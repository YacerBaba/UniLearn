package com.yacer.unilearn.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String img_url;
}
