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

@Service
public class LectureServicesImplement implements LectureServices {
    @Autowired
    private LectureRepository lr;

    @Cacheable(key = "#p0.getId()", value = "LectureID#2")
    public Lecture addLecture(LectureDTO lectureDTO) {
        try {
            Lecture lecture = new Lecture();
            BeanUtils.copyProperties(lectureDTO, lecture);
            lr.save(lecture);
            return lecture;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CacheEvict(key = "#p0", value = "LectureID")
    public boolean deleteLectureById(Long id) {
        try {
            lr.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0", value = "LectureID#2")
    public Lecture findLectureById(Long id) {
        try {
            Lecture lecture = lr.findById(id).get();
            return lecture;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()", value = "LectureID#2")
    public boolean updateLecture(LectureDTO lectureDTO) {
        try {
            Lecture lecture = new Lecture();
            BeanUtils.copyProperties(lectureDTO, lecture);
            lr.save(lecture);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void fillLecture() {
        Lecture a = new Lecture();
        a.setId(123455);
        a.setTeacherID(3101032L);
        lr.save(a);
    }
}