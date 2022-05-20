package com.example.examsys.Services;

import com.example.examsys.DTO.AnswerSheetDTO;
import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Support.MyTool;

import java.util.Date;

public interface AnswerSheetServices {
    AnswerSheet addAnswerSheet(AnswerSheetDTO answerSheetDTO);

    boolean deleteAnswerSheetById(Long id);

    AnswerSheet findAnswerSheetById(Long id);

    boolean updateAnswerSheet(AnswerSheetDTO answerSheetDTO);

    MyTool.Tuple<Date, Date> getTestDuration(AnswerSheetDTO answerSheetDTO);

    void fillAnswerSheet();
}
