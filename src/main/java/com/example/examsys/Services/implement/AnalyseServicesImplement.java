package com.example.examsys.Services.implement;

import com.example.examsys.Entity.AnswerSheet;
import com.example.examsys.Entity.Test;
import com.example.examsys.Repository.TestRepository;
import com.example.examsys.Services.AnalyseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AnalyseServicesImplement implements AnalyseServices {
    @Autowired
    private TestRepository tr;

    @Override
    public HashMap<Long, Integer> questionState(Long testID, Long questionID) {
        HashMap<Long, Integer> result = new HashMap<>();
        //先找到考试信息,获取学生们的答卷
        Test test = tr.findById(testID).get();
        ArrayList<AnswerSheet> answerSheets = test.getAnswersheets();
        //将他们在这道题上的得分情况填写到result上
        for (AnswerSheet answerSheet : answerSheets) {
            result.put(answerSheet.getStudentID(), answerSheet.getScoreByQuestionID(questionID));
        }
        return result;
    }

    @Override
    public HashMap<Long, HashMap<Long, Integer>> questionsState(Long testID, ArrayList<Long> questionsID) {
        HashMap<Long, HashMap<Long, Integer>> result = new HashMap<>();
        //先找到考试信息,获取学生们的答卷
        Test test = tr.findById(testID).get();
        ArrayList<AnswerSheet> answerSheets = test.getAnswersheets();
        //将他们在这些题上的得分情况填写到result上
        for (AnswerSheet answerSheet : answerSheets) {
            HashMap<Long, Integer> t = new HashMap<>();
            for (Long id : questionsID) {
                t.put(id, answerSheet.getScoreByQuestionID(id));
            }
            result.put(answerSheet.getStudentID(), t);
        }
        return result;
    }

    @Override
    public HashMap<Long, HashMap<Long, Integer>> typeState(Long testID, String type) {
        HashMap<Long, HashMap<Long, Integer>> result = new HashMap<>();
//        //先找到考试信息,获取学生们的答卷
//        Test test = tr.findById(testID).get();
//        ArrayList<AnswerSheet> answerSheets = test.getAnswersheets();
//        //将他们在这类题上的得分情况填写到result上
//        for (AnswerSheet answerSheet : answerSheets) {
//            HashMap<Long, Integer> t = new HashMap<>();
//            for (Question question : answerSheet.getSheet().keySet()) {
//                if (question.getType() == type) {
//                    t.put(question.getId(), answerSheet.getScoreByQuestionID(question.getId()));
//                }
//            }
//            result.put(answerSheet.getStudentID(), t);
//        }
        return result;
    }
}
