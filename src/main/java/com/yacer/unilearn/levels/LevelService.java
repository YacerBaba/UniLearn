package com.yacer.unilearn.levels;

import com.yacer.unilearn.entities.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LevelService {
    private final LevelRepository repository;

}
