package com.game.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.game.domain.entity.BoardEntity;
import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Label;

@Repository("PuzzleDaoImpl")
public class PuzzleDaoImpl implements PuzzleDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException {

		String sql = "SELECT puzzle_id, t_puzzle.category_id, category_name, category_class, level_id, "
				+ "height, width " + "FROM t_category, t_puzzle "
				+ "WHERE t_puzzle.category_id = t_category.category_id and t_category.category_id = " + "?" + ";";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper, categoryId);
	}

	@Override
	public List<Label> selectManyByPuzzleId(int puzzleId) throws DataAccessException {
		String sql = "SELECT puzzle_id, t_puzzle.category_id, category_name, category_class, level_id, "
				+ "height, width " + "FROM t_category, t_puzzle "
				+ "WHERE t_puzzle.category_id = t_category.category_id and t_category.category_id = "
				+ "(SELECT category_id FROM t_puzzle WHERE t_puzzle.puzzle_id = ?);";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper, puzzleId);
	}

	@Override
	public IngredientEntity selectPuzzle(int puzzleId) throws DataAccessException {
		String sql = "SELECT t_puzzle.puzzle_id, t_puzzle.height, t_puzzle.width " + "FROM t_puzzle "
				+ "WHERE t_puzzle.puzzle_id = ?";

		RowMapper<IngredientEntity> rowMapper = new BeanPropertyRowMapper<IngredientEntity>(IngredientEntity.class);
		return jdbc.queryForObject(sql, rowMapper, puzzleId);
	}

	@Override
	public List<String> selectManyKW(int puzzleId) throws DataAccessException {
		String sql = "SELECT kw " + "FROM t_puzzle, t_kw_property, t_kw " + "WHERE t_puzzle.puzzle_id = " + "?" + " "
				+ "AND t_puzzle.puzzle_id = t_kw_property.puzzle_id " + "AND t_kw_property.kw_id = t_kw.kw_id;";

		return jdbc.queryForList(sql, String.class, puzzleId);

	}

	@Override
	public String insertPlay(int puzzleId, String lineBoard) throws DataAccessException {
		UUID uuid = UUID.randomUUID();
		String sql = "INSERT INTO t_play (public_id, puzzle_id, line_board, created_at, cleared_at) " + "VALUES("
				+ "?, ?, ?, " + "CURRENT_TIMESTAMP" + " " + ", " + "NULL" + ")";

		jdbc.update(sql, uuid.toString(), puzzleId, lineBoard);

		return uuid.toString();
	}

	@Override
	public int insertManyAnswers(String publicId, int puzzleId, List<AnswerStatus> answers) throws DataAccessException {
		String sql = "INSERT INTO t_answer (order_index, kw_id, play_id, from_id, to_id, answer_flg) "
				+ "VALUES (?, (" + "SELECT t_kw_property.kw_id FROM t_kw_property "
				+ "INNER JOIN t_kw ON t_kw.kw_id = t_kw_property.kw_id "
				+ "INNER JOIN t_puzzle ON t_puzzle.puzzle_id = t_kw_property.puzzle_id "
				+ "WHERE t_kw.kw = ? AND t_kw_property.puzzle_id = ? " + "), " + "( "
				+ "SELECT play_id FROM t_play WHERE public_id = ? " + "), " + "?, ?, FALSE);";

		int count = 0;

		for (AnswerStatus ans : answers) {
			count += jdbc.update(sql, ans.getOrderIndex(), ans.getKw(), puzzleId, publicId, ans.getFromId(),
					ans.getToId());
		}

		System.out.println("affected: " + count);

		return count;
	}

	/**
	 * 
	 * @param playId
	 * @return
	 * @throws DataAccessException
	 * 
	 * 
	 * int orderIndex
	 * String kw
	 * boolean answerFlg
	 * int fromId
	 * int toId;
	 * 
	 */

	@Override
	public List<AnswerStatus> selectManyAnswerStatus(String publicId) throws DataAccessException {

		String sql = "SELECT order_index, kw, answer_flg, from_id, to_id " + "FROM t_play "
				+ "INNER JOIN t_answer ON t_play.play_id = t_answer.play_id "
				+ "INNER JOIN t_kw ON t_kw.kw_id = t_answer.kw_id " + "WHERE t_play.public_id = ?";

		RowMapper<AnswerStatus> rowMapper = new BeanPropertyRowMapper<AnswerStatus>(AnswerStatus.class);
		return jdbc.query(sql, rowMapper, publicId);
	}

	@Override
	public BoardEntity selectBoard(String publicId) throws DataAccessException {

		String sql = "SELECT t_puzzle.puzzle_id, width, height, line_board " + "FROM t_play "
				+ "INNER JOIN t_puzzle ON t_play.puzzle_id = t_puzzle.puzzle_id " + "WHERE public_id = ?";

		RowMapper<BoardEntity> rowMapper = new BeanPropertyRowMapper<BoardEntity>(BoardEntity.class);
		return jdbc.queryForObject(sql, rowMapper, publicId);
	}

	@Override
	public List<AnswerStatus> selectAnswerStatus(String publicId, int fromId, int toId) throws DataAccessException {
		
		String sql = "SELECT order_index, kw, answer_flg, from_id, to_id " + "FROM t_play "
				+ "INNER JOIN t_answer ON t_play.play_id = t_answer.play_id "
				+ "INNER JOIN t_kw ON t_kw.kw_id = t_answer.kw_id "
				+ "WHERE t_play.public_id = ? AND ((from_id = ? AND to_id = ?) or (from_id = ? AND to_id = ?))";
		
		RowMapper<AnswerStatus> rowMapper = new BeanPropertyRowMapper<AnswerStatus>(AnswerStatus.class);
		return jdbc.query(sql, rowMapper, publicId, fromId, toId, toId, fromId);
	}

	@Override
	public int updateAnswer(String publicId, AnswerStatus answer) throws DataAccessException {
		String sql = "UPDATE t_answer SET t_answer.answer_flg = TRUE "
				+ "WHERE from_id = ? AND to_id = ? AND t_answer.play_id IN "
				+ "(SELECT t_play.play_id FROM t_play WHERE public_id = ?)";

		return jdbc.update(sql, answer.getFromId(), answer.getToId(), publicId);
	}
	
	@Override
	public boolean hasCleared (String publicId) throws DataAccessException{
		String sql = "SELECT COUNT(*) FROM t_answer "
				+ "INNER JOIN t_play ON t_play.play_id = t_answer.play_id "
				+ "WHERE t_play.public_id = ? AND t_answer.answer_flg = FALSE";
		
		int count = jdbc.queryForList(sql, Integer.class, publicId).get(0);
		
		return count == 0 ? true : false;
	}
	
	public int updateClear(String publicId) throws DataAccessException{
		String sql = "UPDATE t_play SET cleared_at = CURRENT_TIMESTAMP "
				+ "WHERE public_id = ?";
		
		System.out.println(sql);
		
		return jdbc.update(sql, publicId);
	}
}
