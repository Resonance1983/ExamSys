package com.example.examsys.Entity;

import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;

@Data
@Document(collection = "Test")
public class Test {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("teacherID")
    private long teacherID;
    @Field("lectureID")
    private long lectureID;
    @Field("invigilatorID")
    private long invigilatorID;
    @Field("timeBegin")
    private Date timeBegin;
    @Field("timeFinish")
    private Date timeFinish;
    @Field("batch")
    private int batch;
    @Field("session")
    private int session;
    @Field("studentsID")
    private ArrayList<Long> studentsID;
    @Field("questions")
    private ArrayList<Long> questionsID;
    @Field("answersheets")
    private ArrayList<AnswerSheet> answersheets;
}