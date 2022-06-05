package com.example.examsys.Services;

public interface FlowServices {
    boolean lectureAddStudent(long lectureID, long studentID);

    boolean teacherHoldLecture(long teacherID, long lectureID);
}
