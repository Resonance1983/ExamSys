package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Question")
public class Question {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("type")
    private String type;
    @Field("score")
    private int score;
    @Field("subject")
    private String subject;
    @Field("description")
    private String description;
    @Field("pictureURL")
    private String pictureURL;
    @Field("audioURL")
    private String audioURL;
    @Field("correctAnswer")
    private String correctAnswer;
}