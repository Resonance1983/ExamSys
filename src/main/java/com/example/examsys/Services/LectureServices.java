package com.example.examsys.Services;

import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;

import java.util.ArrayList;

public interface LectureServices {
    Lecture addLecture(LectureDTO lectureDTO);

    boolean deleteLectureById(Long id);

    Lecture findLectureById(Long id);

    boolean updateLecture(LectureDTO lectureDTO);

    ArrayList<Lecture> findAllLectures();

    void fillLecture();
}
