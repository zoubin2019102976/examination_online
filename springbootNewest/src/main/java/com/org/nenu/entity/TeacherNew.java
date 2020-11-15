package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeacherNew extends User{
    private Integer id;
    private String specialty;

    public TeacherNew(){
        this.setRole("teacher");
    }
}
