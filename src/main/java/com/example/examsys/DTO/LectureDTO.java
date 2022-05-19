package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.example.examsys.Entity.Lecture;
import lombok.Data;


@Data
public class LectureDTO implements Serializable {
	private final long id;
    private final String academy;
    private final long teacherID;
    private final ArrayList<Long> students;
    private final double normalscore;
    private final ArrayList<Integer> testscore;

    public LectureDTO(Lecture lecture){
        this.id = lecture.getId();
        this.academy = lecture.getAcademy();
        this.teacherID = lecture.getTeacherID();
        this.students = lecture.getStudents();
        this.normalscore = lecture.getNormalscore();
        this.testscore = lecture.getTestscore();
    }
}
