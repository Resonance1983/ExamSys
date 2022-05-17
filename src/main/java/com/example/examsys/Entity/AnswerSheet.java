package com.example.ExamSys.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@Document(collection = "AnswerSheet")
public class AnswerSheet{
    @Id

    @Field("id")
    private long id;
    @Field("testID")
    private long testID;
    @Field("studentID")
    private long studentID;
    @Field("answers")
    private ArrayList<String> answers;

}