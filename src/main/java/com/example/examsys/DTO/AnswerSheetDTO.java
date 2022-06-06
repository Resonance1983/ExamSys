package com.example.examsys.DTO;

import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class AnswerSheetDTO implements Serializable {
    private final long id;
    private final long testID;
    private final long studentID;
    private final ArrayList<Question> questions;
    private final ArrayList<String> answers;
    private final ArrayList<Integer> scores;

    public AnswerSheetDTO(AnswerSheet answerSheet) {
        this.id = answerSheet.getId();
        this.testID = answerSheet.getTestID();
        this.studentID = answerSheet.getStudentID();
        this.questions = answerSheet.getQuestions();
        this.answers = answerSheet.getAnswers();
        this.scores = answerSheet.getScores();
    }
}
