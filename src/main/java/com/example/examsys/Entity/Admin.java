package com.example.examsys.Entity;


import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@Document(collection = "Admin")
public class Admin {
    @Id

    @Field("id")
    private long id;
    @NotBlank(message = "Name can not be empty.")
    @Field("Name")
    private String Name;
    @Length(min = 8,max = 20,message = "password length from 8 to 20")
    @Field("passWord")
    private String passWord;
    @Field("sex")
    private String sex;
    @Field("pictureURL")
    private String pictureURL;
    @Field("messageBox")
    private ArrayList<String> messageBox;
}