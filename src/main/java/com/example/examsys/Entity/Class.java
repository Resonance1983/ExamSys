package com.example.ExamSys.Entity;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Document(collection = "Class")
public class Class {
    @Id

    @Field("id")
    private long id;
    @Field("academy")
    private String academy;
    @Field("teacherID")
    private long teacherID;
    @Field("students")
    private ArrayList<String> students;
    @Field("normalscore")
    private ArrayList<String> normalscore;
    @Field("testscore")
    private ArrayList<String> testscore;
    @Field("timeBegin")
    private Date timeBegin;
    @Field("timeFinish")
    private Date timeFinish;
}