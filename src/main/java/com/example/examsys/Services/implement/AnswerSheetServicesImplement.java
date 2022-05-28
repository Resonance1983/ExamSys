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

    @Cacheable(key = "#p0.getId()", value = "AnswerSheetID#5")
    public AnswerSheet addAnswerSheet(AnswerSheetDTO answerSheetDTO) {
        AnswerSheet answerSheet = new AnswerSheet();
        BeanUtils.copyProperties(answerSheetDTO, answerSheet);
        ar.save(answerSheet);
        return answerSheet;
    }

    @CacheEvict(key = "#p0", value = "AnswerSheetID")
    public boolean deleteAnswerSheetById(Long id) {
        ar.deleteById(id);
        return true;
    }

    @Cacheable(key = "#p0", value = "AnswerSheetID#5")
    public AnswerSheet findAnswerSheetById(Long id) {
        AnswerSheet answerSheet = ar.findById(id).get();
        return answerSheet;
    }

    @CachePut(key = "#p0.getId()", value = "AnswerSheetID#5")
    public boolean updateAnswerSheet(AnswerSheetDTO answerSheetDTO) {
        AnswerSheet answerSheet = new AnswerSheet();
        BeanUtils.copyProperties(answerSheetDTO, answerSheet);
        ar.save(answerSheet);
        return true;
    }

    @Autowired
    private TestServices testServices;

    public MyTool.Tuple<Date, Date> getTestDuration(AnswerSheetDTO answerSheetDTO) {
        Test test = testServices.findTestById(answerSheetDTO.getTestID());
        return new MyTool.Tuple(test.getTimeBegin(), test.getTimeFinish());
    }

    @Override
    public void fillAnswerSheet() {
        try {
            AnswerSheet answerSheet = new AnswerSheet();
            answerSheet.setStudentID(31901028);
            ar.save(answerSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
