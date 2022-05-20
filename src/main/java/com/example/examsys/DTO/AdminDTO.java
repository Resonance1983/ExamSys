package com.example.examsys.DTO;

import com.example.examsys.Entity.Admin;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class AdminDTO implements Serializable {
    private final long id;
    private final String Name;
    private final String passWord;
    private final String sex;
    private final String pictureURL;
    private final ArrayList<String> messageBox;

    public AdminDTO(Admin admin) {
        this.id = admin.getId();
        this.Name = admin.getName();
        this.passWord = admin.getPassWord();
        this.sex = admin.getSex();
        this.pictureURL = admin.getPictureURL();
        this.messageBox = admin.getMessageBox();
    }
}
