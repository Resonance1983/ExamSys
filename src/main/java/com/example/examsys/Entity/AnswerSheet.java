package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


@Data
@Document(collection = "AnswerSheet")
public class AnswerSheet{
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("testID")
    private long testID;
    @Field("studentID")
    private long studentID;
    @Field("sheet")
    private HashMap<Question,String> sheet;

}