package com.example.examsys.Services.implement;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Entity.Admin;
import com.example.examsys.Repository.AdminRepository;
import com.example.examsys.Services.AdminServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AdminServicesImplement implements AdminServices {
    @Autowired
    private AdminRepository ar;

    @Override
    public Admin findAdminByID(Long id) {
        Admin admin = ar.findById(id).get();
        return admin;
    }

    @Override
    public Admin updateAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminDTO, admin);
        ar.save(admin);
        return admin;
    }

    @Override
    public void fillAdmin() {
        try {
            Admin admin = new Admin();
            admin.setId(new Random().nextLong());
            admin.setName("zwt");
            admin.setSex("male");
            admin.setPassWord("31901031");
            ar.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
