package com.example.examsys.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@Document(collection = "Question")
public class Question{
    @Id
    @Field("id")
    private long id;
    @Field("type")
    private int type;
    @Field("score")
    private int score;
    @Field("description")
    private String description;
    @Field("pictureURL")
    private String pictureURL;
    @Field("audioURL")
    private String audioURL;
    @Field("correctAnswer")
    private  String correctAnswer;
}