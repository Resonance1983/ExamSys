package com.example.examsys.Services;

import java.util.ArrayList;
import java.util.HashMap;

public interface AnalyseServices {
    //考试中单个问题的得分情况
    HashMap<Long, Integer> questionState(Long testID, Long questionID);

    //某些题目的得分情况集合
    HashMap<Long, HashMap<Long, Integer>> questionsState(Long testID, ArrayList<Long> questionsID);

    //考试中某些题型的得分情况
    HashMap<Long, HashMap<Long, Integer>> typeState(Long testID, String type);

}
