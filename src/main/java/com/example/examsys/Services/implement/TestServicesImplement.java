package com.example.examsys.Services.implement;

import com.example.examsys.DTO.TestDTO;
import com.example.examsys.Entity.Test;
import com.example.examsys.Repository.TestRepository;
import com.example.examsys.Services.TestServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestServicesImplement implements TestServices {
    @Autowired
    private TestRepository tr;

    @Cacheable(key = "#p0.getId()",value = "TestID#2")
    public Test addTest(TestDTO testDTO){
        try {
            Test test = new Test();
            BeanUtils.copyProperties(testDTO, test);
            tr.save(test);
            return test;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CacheEvict(key = "#p0",value = "TestID")
    public boolean deleteTestById(Long id){
        try {
            tr.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Cacheable(key = "#p0",value = "TestID#2")
    public Test findTestById(Long id){
        try {
            Test test = tr.findById(id).get();
            return test;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @CachePut(key = "#p0.getId()",value = "TestID#2")
    public boolean updateTest(TestDTO testDTO){
        try {
            Test test = new Test();
            BeanUtils.copyProperties(testDTO,test);
            tr.save(test);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void fillTest(){
        Test a = new Test();

        a.setId(123456L);

        tr.save(a);
    }
}