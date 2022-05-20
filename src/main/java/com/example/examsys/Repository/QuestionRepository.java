package com.example.examsys.Repository;

import com.example.examsys.Entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface QuestionRepository extends MongoRepository<Question,Long> {
    public ArrayList<Question> findAllBySubjectAndType(String Subject,String Type);
}
