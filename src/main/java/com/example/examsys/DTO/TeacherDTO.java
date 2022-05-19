package com.example.examsys.DTO;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;

@Data
public class TeacherDTO implements Serializable {
	private final long id;
    private final String Name;
    private final String passWord;
    private final String sex;
    private final String pictureURL;
    private final ArrayList<String> messageBox;
    private final String academy;
}
