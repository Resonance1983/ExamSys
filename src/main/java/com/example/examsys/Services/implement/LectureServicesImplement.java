package com.example.examsys.Services.implement;

import com.example.examsys.DTO.LectureDTO;
import com.example.examsys.Entity.Lecture;
import com.example.examsys.Repository.LectureRepository;
import com.example.examsys.Services.LectureServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LectureServicesImplement implements LectureServices {
    @Autowired
    private LectureRepository lr;

    @Cacheable(key = "#p0.getId()", value = "LectureID#5")
    public Lecture addLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(lectureDTO, lecture);
        lr.save(lecture);
        return lecture;
    }

    @CacheEvict(key = "#p0", value = "LectureID")
    public boolean deleteLectureById(Long id) {
        lr.deleteById(id);
        return true;
    }

    @Cacheable(key = "#p0", value = "LectureID#5")
    public Lecture findLectureById(Long id) {
        Lecture lecture = lr.findById(id).get();
        return lecture;
    }

    @CachePut(key = "#p0.getId()", value = "LectureID#5")
    public boolean updateLecture(LectureDTO lectureDTO) {
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(lectureDTO, lecture);
        lr.save(lecture);
        return true;
    }

    @Override
    public ArrayList<Lecture> findAllLectures() {
        ArrayList<Lecture> lectures = new ArrayList<>(lr.findAll());
        return lectures;
    }

    public void fillLecture() {
        try {
            Lecture a = new Lecture();
            a.setId(123455);
            a.setTeacherID(3101032L);
            lr.save(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}