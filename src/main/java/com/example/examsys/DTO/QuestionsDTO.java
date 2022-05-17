package com.example.examsys.DTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class QuestionsDTO {
	private final long id;
    private final int type;
    private final String description;
    private final String pictureURL;
    private final String audioURL;
    private final int JudgmentCorrectOption;
    private final int choicenCorrectOption;
    private final ArrayList<String> choiceOption;
    private final ArrayList<String> JudgmentOption;
}
