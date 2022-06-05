package com.example.examsys.Services;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public interface TestServices {
    Test addTest(TestDTO testDTO) throws ParseException;

    Test addTestAutomatic(TestDTO testDTO, String subject, HashMap<String, Integer> typeNumberMap) throws ParseException;

    boolean deleteTestById(Long id);

    Test findTestById(Long id);

    boolean updateTest(TestDTO testDTO) throws ParseException;

    ArrayList<Test> findAllTests();

    void fillTest();
}
