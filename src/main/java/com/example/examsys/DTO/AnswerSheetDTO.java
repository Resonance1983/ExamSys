package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Question;
import com.example.examsys.Support.MyTool;
import lombok.Data;

@Data
public class AnswerSheetDTO implements Serializable {
	private final long id;
    private final long testID;
    private final long studentID;
    private final HashMap<Question, MyTool.Tuple<String,Integer>> sheet;

    public AnswerSheetDTO(AnswerSheet answerSheet){
        this.id = answerSheet.getId();
        this.testID = answerSheet.getTestID();
        this.studentID = answerSheet.getStudentID();
        this.sheet = answerSheet.getSheet();
    }
}
