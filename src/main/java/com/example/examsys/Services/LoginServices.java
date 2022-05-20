package com.example.examsys.Services;

public interface LoginServices {
    boolean studentLogin(long studentID, String password);

    boolean teacherLogin(long teacherID, String password);

    boolean adminLogin(long adminID, String password);

    boolean studentModify(long studentID, String oldpassword, String newpass1, String newpass2);

    boolean teacherModify(long teacherID, String oldpassword, String newpass1, String newpass2);

    boolean adminModify(long adminID, String oldpassword, String newpass1, String newpass2);
}

