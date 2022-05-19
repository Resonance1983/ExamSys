package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.examsys.Entity.Question;
import lombok.Data;

@Data
public class AnswerSheetDTO implements Serializable {
	private final long id;
    private final long testID;
    private final long studentID;
    private final HashMap<Question,String> sheet;
}
