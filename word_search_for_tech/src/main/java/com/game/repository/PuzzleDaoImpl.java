package com.game.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.game.domain.model.Ingredient;
import com.game.domain.model.Label;

@Repository("PuzzleDaoImpl")
public class PuzzleDaoImpl implements PuzzleDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public List<Label> selectMany(int categoryId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public Ingredient selectOne(String categoryId) throws DataAccessException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
