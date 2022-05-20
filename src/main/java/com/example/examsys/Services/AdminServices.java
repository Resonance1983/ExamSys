package com.example.examsys.Services;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Entity.Admin;

public interface AdminServices {
    Admin findAdminByID(Long id);

    Admin updateAdmin(AdminDTO adminDTO);

    void fillAdmin();
}
