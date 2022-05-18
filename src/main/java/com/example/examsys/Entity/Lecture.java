package com.example.examsys.Entity;


import com.example.examsys.Support.MongoAutoID.AutoId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Data
@Document(collection = "lecture")
public class lecture {
    @Id
    @AutoId
    @Field("id")
    private long id;
    @Field("academy")
    private String academy;
    @Field("teacherID")
    private long teacherID;
    @Field("students")
    private ArrayList<String> students;
    @Field("normalscore")
    private double normalscore;
    @Field("testscore")
    private ArrayList<String> testscore;
}
