package com.example.examsys.Entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@Document(collection = "Questions")
public class Questions{
    @Id

    @Field("id")
    private long id;
    @Field("type")
    private int type;
    @Field("description")
    private String description;
    @Field("pictureURL")
    private String pictureURL;
    @Field("audioURL")
    private String audioURL;
    @Field("JudgmentCorrectOption")
    private int JudgmentCorrectOption;
    @Field("choicenCorrectOption")
    private int choicenCorrectOption;
    @Field("choiceOption")
    private ArrayList<String> choiceOption;
    @Field("JudgmentOption")
    private ArrayList<String> JudgmentOption;
}