package com.example.examsys.Services.implement;

import com.example.examsys.Entity.Lecture;
import com.example.examsys.Entity.Student;
import com.example.examsys.Entity.Teacher;
import com.example.examsys.Repository.LectureRepository;
import com.example.examsys.Repository.StudentRepository;
import com.example.examsys.Repository.TeacherRepository;
import com.example.examsys.Services.FlowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FlowServicesImplement implements FlowServices {
    @Autowired
    private LectureRepository lr;
    @Autowired
    private StudentRepository sr;
    @Autowired
    private TeacherRepository tr;


    @Override
    public boolean lectureAddStudent(long lectureID, long studentID) {
        Lecture lecture = lr.findById(lectureID).get();
        Student student = sr.findById(studentID).get();
        try {
            ArrayList<Long> students = lecture.getStudents();
            ArrayList<Long> lectures = student.getLectures();
            if (!students.contains(studentID)) students.add(studentID);
            if (!lectures.contains(lectureID)) lectures.add(lectureID);
            lecture.setStudents(students);
            student.setLectures(lectures);
            lr.save(lecture);
            sr.save(student);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean teacherHoldLecture(long teacherID, long lectureID) {
        Teacher teacher = tr.findById(teacherID).get();
        Lecture lecture = lr.findById(lectureID).get();
        try {
            ArrayList<Long> lectures = teacher.getLectures();
            if (!lectures.contains(lectureID)) lectures.add(lectureID);
            teacher.setLectures(lectures);
            lecture.setTeacherID(teacherID);
            lr.save(lecture);
            tr.save(teacher);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
