package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Administrator extends User{
    private Integer id;

    public Administrator(){
        this.setRole("administrator");
    }
}
