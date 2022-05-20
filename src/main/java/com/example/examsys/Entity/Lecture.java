package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Data
@Document(collection = "lecture")
public class Lecture {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("academy")
    private String academy;
    @Field("teacherID")
    private long teacherID;
    @Field("students")
    private ArrayList<Long> students;
    @Field("normalscore")
    private double normalscore;
    @Field("testscore")
    private ArrayList<Integer> testscore;
}
