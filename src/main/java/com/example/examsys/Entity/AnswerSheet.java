package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;

@Data
@Document(collection = "AnswerSheet")
public class AnswerSheet {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("testID")
    private long testID;
    @Field("studentID")
    private long studentID;
    @Field("questions")
    ArrayList<Question> questions;
    @Field("answers")
    ArrayList<String> answers;
    @Field("scores")
    ArrayList<Integer> scores;

    public int getScoreByQuestionID(Long questionID) {
        int index = 0;
        for (Question q : questions) {
            if (q.getId() == questionID)
                return scores.get(index);

            index++;
        }
        return 1;
    }

}