package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import com.example.examsys.Support.MyTool;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashMap;

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
    @Field("sheet")
    private HashMap<Question, MyTool.Tuple<String, Integer>> sheet;

    public int getScoreByQuestionID(Long questionID) {
        for (Question question : sheet.keySet()) {
            if (question.getId() == questionID)
                return sheet.get(question).y;
        }
        return 1;
    }

}