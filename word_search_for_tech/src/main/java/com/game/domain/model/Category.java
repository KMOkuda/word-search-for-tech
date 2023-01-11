package com.game.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Category {
	String categoryName;
	List<Quiz> quiz;
}
