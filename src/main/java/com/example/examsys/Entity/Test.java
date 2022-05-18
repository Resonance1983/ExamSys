package com.example.examsys.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


@Data
@Document(collection = "Test")
public class Test{
    @Id

    @Field("id")
    private long id;
    @Field("teacherID")
    private long teacherID;
    @Field("classlD")
    private long classlD;
    @Field("invigilatorlD")
    private long invigilatorlD;
    @Field("timeBegin")
    private Date timeBegin;
    @Field("timeFinish")
    private Date timeFinish;
    @Field("duration")
    private Date duration;
    @Field("batch")
    private int batch;
    @Field("session")
    private int session;
    @Field("score")
    private double score;
    @Field("students")
    private ArrayList<String> students;
    @Field("questions")
    private ArrayList<String> questions;
    @Field("answersheets")
    private ArrayList<String> answersheets;
}