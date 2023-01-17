package com.game.domain.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	//ログイン処理も含まれる
	int getLongestDuration(String userId);
	int getCurrentDuration(String userId);
}
