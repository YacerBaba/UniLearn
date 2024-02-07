package com.yacer.unilearn.modules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {

    private final ModuleDtoConverter converter;
    private final ModuleDetailsDtoConverter moduleDetailsDtoConverter;
    private final ModuleRepository repository;

    public List<ModuleDTO> getAllModules() {
        var modules = repository.findAll();
        return converter.convertModulesToDTOsList(modules);
    }

    public ModuleDetailsDTO getModuleDetailsById(Integer id) {
        var module = repository.findById(id).orElseThrow();
        return moduleDetailsDtoConverter.convertToDto(module);
    }
}
