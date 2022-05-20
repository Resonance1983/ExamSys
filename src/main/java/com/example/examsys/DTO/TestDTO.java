package com.example.examsys.DTO;

import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Test;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
public class TestDTO implements Serializable {
    private final long id;
    private final long teacherID;
    private final long lecturelD;
    private final long invigilatorlD;
    private final Date timeBegin;
    private final Date timeFinish;
    private final int batch;
    private final int session;
    private final ArrayList<Long> studentsID;
    private final ArrayList<Long> questionsID;
    private final ArrayList<AnswerSheet> answersheets;

    public TestDTO(Test test) {
        this.id = test.getId();
        this.teacherID = test.getTeacherID();
        this.lecturelD = test.getLecturelD();
        this.invigilatorlD = test.getInvigilatorlD();
        this.timeBegin = test.getTimeBegin();
        this.timeFinish = test.getTimeFinish();
        this.batch = test.getBatch();
        this.session = test.getSession();
        this.studentsID = test.getStudentsID();
        this.questionsID = test.getQuestionsID();
        this.answersheets = test.getAnswersheets();
    }
}
