package com.example.examsys.Services;

public interface LoginServices {
    public boolean studentLogin(long studentID, String password);
    public boolean teacherLogin(long teacherID,String password);
    public boolean adminLogin(long adminID, String password);
    public boolean studentModify(long studentID,String oldpassword,String newpass1,String newpass2);
    public boolean teacherModify(long teacherID,String oldpassword,String newpass1,String newpass2);
    public boolean adminModify(long adminID,String oldpassword,String newpass1,String newpass2);
}

