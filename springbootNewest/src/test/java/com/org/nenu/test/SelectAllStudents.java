package com.org.nenu.test;


import com.org.nenu.entity.StudentNew;
import com.org.nenu.mapper.StuMapper;
import com.org.nenu.mapper.StudentMapper;
import com.org.nenu.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectAllStudents {

    @Autowired
    StudentMapper studentMapper;

    private Logger logger = LoggerFactory.getLogger(SelectAllStudents.class);

    @Test
    public void selectAll(){
        ArrayList<StudentNew> students = studentMapper.getAllStudent();
        for (StudentNew student : students){
            logger.info(student.getId() + " ," + student.getName());
        }
    }
}
