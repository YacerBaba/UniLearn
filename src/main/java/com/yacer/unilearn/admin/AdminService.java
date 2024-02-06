package com.yacer.unilearn.admin;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDtoConverter converter;
    private final AdminRepository repository;

    public AdminDTO getAdminProfile(Integer id) {
        var admin = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such admin with id : " + id));
        return converter.convertToDTO(admin);
    }
}
