package com.example.examsys.Services;

import java.util.HashMap;

public interface AnalyseServices {
    //考试中单个问题的得分情况
    public HashMap<Long,Integer> questionState(Long testID,Long questionID);
    //考试中某些题型的得分情况
    public HashMap<Long,Integer> typeState(Long testID,String type);
    //某些题目的得分情况集合（本服务所有接口都基于此）

}
