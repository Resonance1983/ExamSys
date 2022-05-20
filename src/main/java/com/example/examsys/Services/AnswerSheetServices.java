package com.example.examsys.Services;

import com.example.examsys.DTO.AnswerSheetDTO;
import com.example.examsys.DTO.StudentDTO;
import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Student;
import com.example.examsys.Support.MyTool;

import java.util.Date;

public interface AnswerSheetServices {
    public AnswerSheet addAnswerSheet(AnswerSheetDTO answerSheetDTO);
    public boolean deleteAnswerSheetById(Long id);
    public AnswerSheet findAnswerSheetById(Long id);
    public boolean updateAnswerSheet(AnswerSheetDTO answerSheetDTO);
    public MyTool.Tuple<Date, Date> getTestDuration(AnswerSheetDTO answerSheetDTO);
    public void fillAnswerSheet();
}
