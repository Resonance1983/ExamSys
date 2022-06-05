package com.example.examsys.Services.implement;

import com.example.examsys.DTO.QuestionDTO;
import com.example.examsys.Entity.Question;
import com.example.examsys.Repository.QuestionRepository;
import com.example.examsys.Services.QuestionsServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionsServicesImplement implements QuestionsServices {
    @Autowired
    private QuestionRepository qr;

    @Cacheable(key = "#p0.getId()", value = "QuestionID#5")
    public Question addQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        qr.save(question);
        return question;
    }

    @CacheEvict(key = "#p0", value = "QuestionID")
    public boolean deleteQuestionById(Long id) {
        qr.deleteById(id);
        return true;
    }

    @Cacheable(key = "#p0", value = "QuestionID#5")
    public Question findQuestionById(Long id) {
        Question question = qr.findById(id).get();
        return question;
    }

    @Override
    public ArrayList<Question> findQuestionsById(ArrayList<Long> ids) {
        ArrayList<Question> questions = new ArrayList<Question>();
        for (Long id : ids) {
            Question t = qr.findById(id).get();
            questions.add(t);
        }
        return questions;
    }

    @Override
    public ArrayList<Question> findAllQuestion() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions = new ArrayList<Question>(qr.findAll());
        return questions;
    }

    @CachePut(key = "#p0.getId()", value = "QuestionID#5")
    public boolean updateQuestion(QuestionDTO questionDTO) {
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO, question);
        qr.save(question);
        return true;
    }

    public void fillQuestion() {
        Question a = new Question();
        a.setType("简答题");
        a.setDescription("光的散射原因");
        qr.save(a);
    }
}