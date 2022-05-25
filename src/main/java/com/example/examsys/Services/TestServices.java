package com.example.examsys.Services;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;

import java.util.ArrayList;
import java.util.HashMap;

public interface TestServices {
    Test addTest(TestDTO testDTO);

    Test addTestAutomatic(TestDTO testDTO, String subject, HashMap<String, Integer> typeNumberMap);

    boolean deleteTestById(Long id);

    Test findTestById(Long id);

    boolean updateTest(TestDTO testDTO);

    ArrayList<Test> findAllTests();

    void fillTest();
}
