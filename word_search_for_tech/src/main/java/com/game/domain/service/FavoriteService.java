package com.game.domain.service;

import java.util.List;

public interface FavoriteService {
	int registerFavorite(String userId, List<String> KWList);
	List<String> getFavorite(String userId);
}
