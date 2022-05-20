package com.example.examsys.Services;

import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;

public interface LectureServices {
    Lecture addLecture(LectureDTO lectureDTO);

    boolean deleteLectureById(Long id);

    Lecture findLectureById(Long id);

    boolean updateLecture(LectureDTO lectureDTO);

    void fillLecture();
}
