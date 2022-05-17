package com.example.examsys.DTO;

import java.util.ArrayList;
import lombok.Data;

@Data
public class AnswerSheetDTO {
	private final long id;
    private final long testID;
    private final long studentID;
    private final ArrayList<String> answers;
}
