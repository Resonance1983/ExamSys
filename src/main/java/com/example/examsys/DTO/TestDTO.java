package com.example.examsys.DTO;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

@Data
public class TestDTO {
	private final long id;
    private final long teacherID;
    private final long classlD;
    private final long invigilatorlD;
    private final Date timeBegin;
    private final Date timeFinish;
    private final Date duration;
    private final int batch;
    private final int session;
    private final double score;
    private final ArrayList<String> students;
    private final ArrayList<String> questions;
    private final ArrayList<String> answersheets;
}
