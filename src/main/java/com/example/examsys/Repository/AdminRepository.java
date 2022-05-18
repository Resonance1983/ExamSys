package com.example.examsys.Repository;

import com.example.examsys.Entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin,Long> {
}
