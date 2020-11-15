package com.org.nenu.mapper;

import com.org.nenu.entity.TeacherNew;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface TeacherMapper {
    TeacherNew getTeacherByAccountNumber(String account_number);
    ArrayList<TeacherNew> getAllTeacher();
    int insertTeacher(TeacherNew teacherNew);
}
