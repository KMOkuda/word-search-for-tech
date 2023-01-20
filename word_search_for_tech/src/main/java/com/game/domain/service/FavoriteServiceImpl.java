package com.game.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.game.domain.model.suspended.Favorite;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Override
	public int registerFavorite(String userId, List<String> KWList) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public int registerFavoriteByParam(String userId, Map<String,String> KWList) {
		return 1;
	};

	@Override
	public List<Favorite> getFavorite(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
