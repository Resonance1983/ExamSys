package com.example.examsys.Services.implement;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Question;
import com.example.examsys.Entity.Test;
import com.example.examsys.Repository.QuestionRepository;
import com.example.examsys.Repository.TestRepository;
import com.example.examsys.Services.TestServices;
import com.example.examsys.Support.MyTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class TestServicesImplement implements TestServices {
    @Autowired
    private TestRepository tr;
    @Autowired
    private QuestionRepository qr;

    @Cacheable(key = "#p0.getId()", value = "TestID#2")
    public Test addTest(TestDTO testDTO) {
        try {
            Test test = new Test();
            BeanUtils.copyProperties(testDTO, test);
            tr.save(test);
            return test;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Test addTestAutomatic(TestDTO testDTO, String subject, HashMap<String, Integer> typeNumberMap) {
        //首先找到所有的相关学科和类型的问题
        HashMap<String, ArrayList<Question>> relatedQuestions = new HashMap<>();
        //组成的问卷
        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Long> questionsID = new ArrayList<>();
        for (String type : typeNumberMap.keySet()) {
            relatedQuestions.put(type, qr.findAllBySubjectAndType(subject, type));
        }
        //然后根据问题个数随机取出组成问卷
        for (String type : typeNumberMap.keySet()) {
            int number = typeNumberMap.get(type);
            questions.addAll(MyTool.randomGetQuestions(relatedQuestions.get(type), number));
        }
        //然后取出id交给test,上传到数据库
        for (Question question : questions) {
            questionsID.add(question.getId());
        }
        Test test = new Test();
        BeanUtils.copyProperties(testDTO, test);
        test.setQuestionsID(questionsID);
        tr.save(test);
        return test;
    }

    @CacheEvict(key = "#p0", value = "TestID")
    public boolean deleteTestById(Long id) {
        try {
            tr.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0", value = "TestID#2")
    public Test findTestById(Long id) {
        try {
            Test test = tr.findById(id).get();
            return test;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()", value = "TestID#2")
    public boolean updateTest(TestDTO testDTO) {
        try {
            Test test = new Test();
            BeanUtils.copyProperties(testDTO, test);
            tr.save(test);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Test> findAllTests() {
        ArrayList<Test> tests = new ArrayList<>(tr.findAll());
        return tests;
    }

    public void fillTest() {
        Test a = new Test();

        a.setId(123456L);

        tr.save(a);
    }
}