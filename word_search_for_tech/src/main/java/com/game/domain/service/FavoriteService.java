package com.game.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.game.domain.model.Favorite;

@Service
public interface FavoriteService {
	int registerFavorite(String userId, List<String> KWList);

	int registerFavoriteByParam(String userId, Map<String,String> KWList);

	List<Favorite> getFavorite(String userId);
}
