package com.game.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.game.domain.entity.IngredientEntity;
import com.game.domain.model.AnswerStatus;
import com.game.domain.model.Label;

@Repository("PuzzleDaoImpl")
public class PuzzleDaoImpl implements PuzzleDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ

		String sql = "SELECT puzzle_id, t_puzzle.category_id, category_name, category_class, level_id, "
				+ "height, width " + "FROM t_category, t_puzzle "
				+ "WHERE t_puzzle.category_id = t_category.category_id and t_category.category_id = "
				+ Integer.toString(categoryId) + ";";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper);
	}

	@Override
	public List<Label> selectManyByPID(int puzzleId) throws DataAccessException {
		String sql = "SELECT puzzle_id, t_puzzle.category_id, category_name, category_class, level_id, "
				+ "height, width " + "FROM t_category, t_puzzle "
				+ "WHERE t_puzzle.category_id = t_category.category_id and t_category.category_id = "
				+ "(SELECT category_id FROM t_puzzle WHERE t_puzzle.puzzle_id = " + puzzleId + ");";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper);
	}

	@Override
	public IngredientEntity selectOne(int puzzleId) throws DataAccessException {
		String sql = "SELECT t_puzzle.puzzle_id, t_puzzle.height, t_puzzle.width " + "FROM t_puzzle "
				+ "WHERE t_puzzle.puzzle_id = ?";

		RowMapper<IngredientEntity> rowMapper = new BeanPropertyRowMapper<IngredientEntity>(IngredientEntity.class);
		return jdbc.queryForObject(sql, rowMapper, puzzleId);
	}

	@Override
	public List<String> selectKW(int puzzleId) throws DataAccessException {
		String sql = "SELECT kw " + "FROM t_puzzle, t_kw_property, t_kw " + "WHERE t_puzzle.puzzle_id = " + puzzleId
				+ " " + "AND t_puzzle.puzzle_id = t_kw_property.puzzle_id " + "AND t_kw_property.kw_id = t_kw.kw_id;";

		// RowMapper<String> rowMapper = new
		// BeanPropertyRowMapper<String>(String.class);
		return jdbc.queryForList(sql, String.class);

	}

	@Override
	public String insertOne(int puzzleId) throws DataAccessException {
		UUID uuid = UUID.randomUUID();
		String sql = "INSERT INTO t_play (public_id, puzzle_id, created_at, cleared_at) " + "VALUES("
				+ "?" + ", ?, " + "CURRENT_TIMESTAMP" + " " + ", " + "NULL" + ")";

		jdbc.update(sql, uuid.toString(), puzzleId);

		return uuid.toString();
	}

	public String insertMany(String publicId, List<AnswerStatus> answers) throws DataAccessException {
		String sql = "INSERT INTO t_answer (order_index, puzzle_kw_id, play_id, from_id, to_id, answer_flg) " +
					"VALUES (?, (SELECT ))"
					+"SELECT order_index, puzzle_kw_id, play_id, from_id, to_id, answer_flg "
					+ "FROM t_play, t_kw_property "
					+"INNER JOIN t_kw ON t_kw_property.kw_id = t_kw.kw_id "
					+"WHERE t_kw.kw_id = ? AND t_kw_property.puzzle_id = ?)";

		for(AnswerStatus ans: answers) {
			jdbc.update(sql, publicId);
		}
	}

}
