package com.org.nenu.mapper;

import com.org.nenu.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StuMapper {
    int insertToStu(Student student); //注册
    Student selectStudentByStuNum(String stuNum); //根据学号找人
    List<Student> selectAllStudents(); //列出所有学生
}
