package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class QuestionsDTO implements Serializable {
	private final long id;
    private final int type;
    private final int score;
    private final String description;
    private final String pictureURL;
    private final String audioURL;
    private final String correctAnswer;
}
