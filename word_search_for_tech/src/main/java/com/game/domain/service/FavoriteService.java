package com.game.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Favorite;

@Service
public interface FavoriteService {
	int registerFavorite(String userId, List<String> KWList);
	List<Favorite> getFavorite(String userId);
}
