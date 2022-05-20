package com.example.examsys.DTO;

import com.example.examsys.Entity.Teacher;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class TeacherDTO implements Serializable {
    private final long id;
    private final String Name;
    private final String passWord;
    private final String sex;
    private final String pictureURL;
    private final ArrayList<String> messageBox;
    private final String academy;

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.Name = teacher.getName();
        this.passWord = teacher.getPassWord();
        this.sex = teacher.getSex();
        this.pictureURL = teacher.getPictureURL();
        this.messageBox = teacher.getMessageBox();
        this.academy = teacher.getAcademy();
    }
}
