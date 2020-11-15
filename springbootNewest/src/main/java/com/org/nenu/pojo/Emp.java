package com.org.nenu.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Emp {
    private Integer empId;
    private String empName;
    private Integer empAge;

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAge=" + empAge +
                '}';
    }
}