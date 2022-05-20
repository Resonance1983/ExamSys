package com.example.examsys.Support;

import com.example.examsys.Entity.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyTool {
    public static ArrayList<Question> randomGetQuestions(ArrayList<Question> questions, int number){
        HashMap<Integer,String> map = new HashMap();
        ArrayList<Question> results = new ArrayList();
        if (questions.size() <= number) {
            return questions;
        } else {
            while (map.size() < number) {
                int random = (int)(Math.random() * questions.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    results.add(questions.get(random));
                }
            }
            return results;
        }
    }
}
