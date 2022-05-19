package com.example.examsys.Services;

import com.example.examsys.DTO.QuestionDTO;
import com.example.examsys.Entity.Question;

public interface QuestionsServices {
    public Question addQuestion(QuestionDTO questionDTO);
    public boolean deleteQuestionById(Long id);
    public Question findQuestionById(Long id);
    public boolean updateQuestion(QuestionDTO questionDTO);
    public void fillQuestion();
}
