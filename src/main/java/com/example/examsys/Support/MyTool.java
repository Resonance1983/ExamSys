package com.example.examsys.Support;

import com.example.examsys.Entity.Question;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//工具类
public class MyTool {
    public static ArrayList<Question> randomGetQuestions(ArrayList<Question> questions, int number) {
        HashMap<Integer, String> map = new HashMap();
        ArrayList<Question> results = new ArrayList();
        if (questions.size() <= number) {
            return questions;
        } else {
            while (map.size() < number) {
                int random = (int) (Math.random() * questions.size());
                if (!map.containsKey(random)) {
                    map.put(random, "");
                    results.add(questions.get(random));
                }
            }
            return results;
        }
    }

    public static boolean inDuration(Date nowTime, MyTool.Tuple<Date, Date> duration) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(duration.x);

        Calendar end = Calendar.getInstance();
        end.setTime(duration.y);

        return date.after(begin) && date.before(end);
    }

    public static class Tuple<X, Y> {
        public X x;
        public Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
    
}
