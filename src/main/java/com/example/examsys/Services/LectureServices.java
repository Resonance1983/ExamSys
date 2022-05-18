package com.example.examsys.Services;

import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;

public interface LectureServices {
    public Lecture addLecture(LectureDTO lectureDTO);
    public boolean deleteLectureById(Long id);
    public Lecture findLectureById(Long id);
    public boolean updateLecture(LectureDTO lectureDTO);
    public void fillLecture();
}
