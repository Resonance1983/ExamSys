package com.example.examsys.Services;

import com.example.examsys.DTO.AdminDTO;
import com.example.examsys.Entity.Admin;

public interface AdminServices {
    public Admin findAdminByID(Long id);
    public Admin updateAdmin(AdminDTO adminDTO);
    public void fillAdmin();
}
