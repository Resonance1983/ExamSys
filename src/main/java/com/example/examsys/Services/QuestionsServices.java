package com.example.examsys.Services;

import com.example.examsys.DTO.QuestionDTO;
import com.example.examsys.Entity.Question;

import java.util.ArrayList;

public interface QuestionsServices {
    Question addQuestion(QuestionDTO questionDTO);

    boolean deleteQuestionById(Long id);

    Question findQuestionById(Long id);

    ArrayList<Question> findQuestionsById(ArrayList<Long> ids);

    ArrayList<Question> findAllQuestion();

    boolean updateQuestion(QuestionDTO questionDTO);

    void fillQuestion();
}
