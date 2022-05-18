package com.example.examsys.Services;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;

public interface TestServices {
    public Test addTest(TestDTO testDTO);
    public boolean deleteTestById(Long id);
    public Test findTestById(Long id);
    public boolean updateTest(TestDTO testDTO);
    public void fillTest();
}
