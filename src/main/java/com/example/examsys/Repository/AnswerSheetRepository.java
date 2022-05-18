package com.example.examsys.Repository;

import com.example.examsys.Entity.AnswerSheet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerSheetRepository extends MongoRepository<AnswerSheet,Long> {
}
