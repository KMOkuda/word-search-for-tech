package com.game.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.game.domain.model.Category;
import com.game.domain.model.KW;
import com.game.domain.model.Level;
import com.game.domain.model.PlayRecord;
import com.game.domain.model.PuzzleInfo;

@Service
public interface PuzzleService {
	List<Category> getCategories();

	List<Level> getLevels();

	List<PuzzleInfo> getPuzzleInfos(String filterMode, String filterId);

	PuzzleInfo getPuzzleInfoByPuzzleId(String puzzleId, boolean firstBlind);

	PuzzleInfo getPuzzleInfoByPlayId(String username, String publicPlayId);

	boolean checkFirstBlind(String username, String puzzleId);

	//publicPlayIdを返す
	int createPlayRecord(String string, String puzzleId, String display);

	PlayRecord getPlayRecord(String username, String publicPlayId);

	int checkAnswerCode(String username, String publicPlayId, String answerCode);

	List<KW> getKWList(String username, String publicPlayId);

	PlayRecord RegisterClear(String username, String publicPlayId, LocalDateTime now);


}
