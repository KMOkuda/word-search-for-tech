package com.game.domain.service;

public interface UserService {
	//ログイン処理も含まれる
	int getLongestDuration(String userId);
	int getCurrentDuration(String userId);
}
