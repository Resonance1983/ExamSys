package com.example.examsys.DTO;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class TestDTO {
	private final long id;
    private final long teacherID;
    private final long lecturelD;
    private final long invigilatorlD;
    private final Date timeBegin;
    private final Date timeFinish;
    private final int batch;
    private final int session;
    private final double score;
    private final ArrayList<Long> studentsID;
    private final ArrayList<Long> questionsID;
    private final ArrayList<String> answersheets;
}
