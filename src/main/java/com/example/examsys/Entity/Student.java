package com.example.examsys.Entity;

import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Document(collection = "Student")
public class Student {
    @Id
    @Field("id")
    @AutoId
    private long id;
    @NotBlank(message = "Name can not be empty.")
    @Field("Name")
    private String Name;
    @Length(min = 8, max = 20, message = "password length from 8 to 20")
    @Field("passWord")
    private String passWord;
    @Field("academy")
    private String academy;
    @Field("major")
    private String major;
    @Field("lectures")
    private ArrayList<Long> lectures;
    @Field("grade")
    private int grade;
    @Field("sex")
    private String sex;
    @Field("pictureURL")
    private String pictureURL;
    @Field("messageBox")
    private ArrayList<String> messageBox;
}