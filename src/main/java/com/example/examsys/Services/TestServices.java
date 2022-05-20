package com.example.examsys.Services;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;

import java.util.HashMap;

public interface TestServices {
    public Test addTest(TestDTO testDTO);
    public Test addTestAutomatic(TestDTO testDTO,String subject, HashMap<String,Integer> typeNumberMap);
    public boolean deleteTestById(Long id);
    public Test findTestById(Long id);
    public boolean updateTest(TestDTO testDTO);
    public void fillTest();
}
