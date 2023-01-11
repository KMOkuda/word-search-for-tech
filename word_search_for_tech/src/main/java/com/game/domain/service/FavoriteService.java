package com.game.domain.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface FavoriteService {
	int registerFavorite(String userId, List<String> KWList) throws DataAccessException;
	List<String> getFavorite(String userId) throws DataAccessException;
}
