package com.example.examsys.Repository;

import com.example.examsys.Entity.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LectureRepository extends MongoRepository<Lecture,Long> {
}
