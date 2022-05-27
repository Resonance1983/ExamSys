package com.example.examsys.DTO;

import com.example.examsys.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class StudentDTO implements Serializable {
    private final long id;
    private final String Name;
    private final String passWord;
    private final String academy;
    private final String major;
    private final int grade;
    private final String sex;
    private final String pictureURL;
    private final ArrayList<String> messageBox;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.Name = student.getName();
        this.passWord = student.getPassWord();
        this.academy = student.getAcademy();
        this.major = student.getMajor();
        this.grade = student.getGrade();
        this.sex = student.getSex();
        this.pictureURL = student.getPictureURL();
        this.messageBox = student.getMessageBox();
    }
}
