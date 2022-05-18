package com.example.examsys.DTO;

import java.util.ArrayList;
import java.util.Date;
import lombok.Data;


@Data
public class LectureDTO {
	private final long id;
    private final String academy;
    private final long teacherID;
    private final ArrayList<String> students;
    private final ArrayList<String> normalscore;
    private final ArrayList<String> testscore;
    private final Date timeBegin;
    private final Date timeFinish;
}
