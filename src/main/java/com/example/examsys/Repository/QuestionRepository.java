package com.example.examsys.Repository;

import com.example.examsys.Entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question,Long> {
}
