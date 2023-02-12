package com.game.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.game.domain.model.Ingredient;
import com.game.domain.model.Label;

@Repository("PuzzleDaoImpl")
public class PuzzleDaoImpl implements PuzzleDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Label> selectManyByCategory(int categoryId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		
		String sql = "SELECT * FROM m_user";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper);
	}
	
	@Override
	public List<Label> selectManyByPID(int categoryId) throws DataAccessException {
		String sql = "SELECT * FROM m_user";

		RowMapper<Label> rowMapper = new BeanPropertyRowMapper<Label>(Label.class);
		return jdbc.query(sql, rowMapper);
	}

	@Override
	public Ingredient selectOne(String categoryId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
