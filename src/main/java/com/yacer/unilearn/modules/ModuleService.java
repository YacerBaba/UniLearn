package com.yacer.unilearn.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleDtoConverter converter;
    private final ModuleRepository repository;

    public List<ModuleDTO> getAllModules() {
        var modules = repository.findAll();
        return converter.convertModulesToDTOsList(modules);
    }
}
