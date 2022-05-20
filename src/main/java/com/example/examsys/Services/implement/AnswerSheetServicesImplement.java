package com.example.examsys.Services.implement;

import com.example.examsys.DTO.AnswerSheetDTO;
import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Test;
import com.example.examsys.Repository.AnswerSheetRepository;
import com.example.examsys.Services.AnswerSheetServices;
import com.example.examsys.Services.TestServices;
import com.example.examsys.Support.MyTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnswerSheetServicesImplement implements AnswerSheetServices {
    @Autowired
    private AnswerSheetRepository ar;

    @Cacheable(key = "#p0.getId()", value = "AnswerSheetID#2")
    public AnswerSheet addAnswerSheet(AnswerSheetDTO answerSheetDTO) {
        try {
            AnswerSheet answerSheet = new AnswerSheet();
            BeanUtils.copyProperties(answerSheetDTO, answerSheet);
            ar.save(answerSheet);
            return answerSheet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CacheEvict(key = "#p0", value = "AnswerSheetID")
    public boolean deleteAnswerSheetById(Long id) {
        try {
            ar.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0", value = "AnswerSheetID#2")
    public AnswerSheet findAnswerSheetById(Long id) {
        try {
            AnswerSheet answerSheet = ar.findById(id).get();
            return answerSheet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()", value = "AnswerSheetID#2")
    public boolean updateAnswerSheet(AnswerSheetDTO answerSheetDTO) {
        try {
            AnswerSheet answerSheet = new AnswerSheet();
            BeanUtils.copyProperties(answerSheetDTO, answerSheet);
            ar.save(answerSheet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Autowired
    private TestServices testServices;

    public MyTool.Tuple<Date, Date> getTestDuration(AnswerSheetDTO answerSheetDTO) {
        Test test = testServices.findTestById(answerSheetDTO.getTestID());
        return new MyTool.Tuple(test.getTimeBegin(), test.getTimeFinish());
    }

    @Override
    public void fillAnswerSheet() {
        AnswerSheet answerSheet = new AnswerSheet();
        answerSheet.setStudentID(31901028);
        ar.save(answerSheet);
    }

}
