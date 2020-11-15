package com.org.nenu.mapper;

import com.org.nenu.entity.Specialty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Mapper
@Component
public interface SpecialtyMapper {
    ArrayList<Specialty> getAllSpecialties();
}
