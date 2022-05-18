package com.example.examsys.Services;

import com.example.examsys.DTO.QuestionsDTO;
import com.example.examsys.Entity.Question;

public interface QuestionsServices {
    public Question addQuestion(QuestionsDTO questionsDTO);
    public boolean deleteQuestionById(Long id);
    public Question findQuestionById(Long id);
    public boolean updateQuestion(QuestionsDTO questionsDTO);
    public void fillQuestion();
}
