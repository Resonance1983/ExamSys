package com.example.examsys.Services;

import com.example.examsys.DTO.QuestionDTO;
import com.example.examsys.Entity.Question;

import java.util.ArrayList;

public interface QuestionsServices {
    public Question addQuestion(QuestionDTO questionDTO);
    public boolean deleteQuestionById(Long id);
    public Question findQuestionById(Long id);
    public ArrayList<Question> findQuestionsById(ArrayList<Long> ids);
    public boolean updateQuestion(QuestionDTO questionDTO);
    public void fillQuestion();
}
