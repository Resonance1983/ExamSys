package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import com.example.examsys.Entity.Question;
import lombok.Data;

@Data
public class QuestionDTO implements Serializable {
	private final long id;
    private final int type;
    private final int score;
    private final String description;
    private final String pictureURL;
    private final String audioURL;
    private final String correctAnswer;

    public QuestionDTO(Question question){
        this.id = question.getId();
        this.type = question.getType();
        this.score = question.getScore();
        this.description = question.getDescription();
        this.pictureURL = question.getPictureURL();
        this.audioURL = question.getAudioURL();
        this.correctAnswer = question.getCorrectAnswer();
    }
}
