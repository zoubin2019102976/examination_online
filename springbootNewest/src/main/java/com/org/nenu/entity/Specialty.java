package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Specialty {
    private Integer id;
    private String specialty;
}
