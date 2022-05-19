package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import lombok.Data;


@Data
public class LectureDTO implements Serializable {
	private final long id;
    private final String academy;
    private final long teacherID;
    private final ArrayList<String> students;
    private final double normalscore;
    private final ArrayList<String> testscore;

}
