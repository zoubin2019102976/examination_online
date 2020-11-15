package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class StudentNew extends User{
    private Integer id;
    private String specialty;

    public StudentNew(){
        this.setRole("student");
    }
}
