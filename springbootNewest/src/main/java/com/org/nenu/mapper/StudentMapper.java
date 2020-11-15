package com.org.nenu.mapper;

import com.org.nenu.entity.StudentNew;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface StudentMapper {
    int insertStudent(StudentNew studentNew);
    StudentNew getStudentByAccountNumber(String account_number);
    ArrayList<StudentNew> getAllStudent();
}
