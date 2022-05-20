package com.example.examsys.DTO;

import com.example.examsys.Entity.Question;
import lombok.Data;

import java.io.Serializable;

@Data
public class QuestionDTO implements Serializable {
    private final long id;
    private final String type;
    private final int score;
    private String subject;
    private final String description;
    private final String pictureURL;
    private final String audioURL;
    private final String correctAnswer;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.type = question.getType();
        this.score = question.getScore();
        this.subject = question.getSubject();
        this.description = question.getDescription();
        this.pictureURL = question.getPictureURL();
        this.audioURL = question.getAudioURL();
        this.correctAnswer = question.getCorrectAnswer();
    }
}
