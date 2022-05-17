package com.example.examsys.DTO;

import java.util.ArrayList;

import lombok.Data;

@Data
public class StudentDTO {
	private final long id;
    private final String Name;
    private final String passWord;
    private final String academy;
    private final String major;
    private final String grade;
    private final String sex;
    private final String pictureURL;
    private final ArrayList<String> messageBox;
}
