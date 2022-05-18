package com.example.examsys.Repository;

import com.example.examsys.Entity.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestRepository extends MongoRepository<Test,Long> {
}
