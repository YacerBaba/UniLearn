package com.yacer.unilearn.admin;

import com.yacer.unilearn.entities.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminDtoConverter {
    public AdminDTO convertToDTO(Admin admin) {
        return new AdminDTO(admin.getId(), admin.getUser().getFirstName()
                , admin.getUser().getLastName(), admin.getUser().getEmail(), admin.getUser().getImg_url());
    }
}
