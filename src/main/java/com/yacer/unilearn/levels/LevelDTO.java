package com.yacer.unilearn.levels;

import com.yacer.unilearn.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LevelDTO {
    private Integer level_id;
    private LevelEnum level_name;
}
