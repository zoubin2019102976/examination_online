package com.org.nenu.test;


import com.org.nenu.mapper.StuMapper;
import com.org.nenu.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SelectOneStudentNew {

    @Autowired
    private StuMapper stuMapper;

    private Logger logger = LoggerFactory.getLogger(SelectOneStudentNew.class);

    @Test
    public void selectOne(){
        Student student = stuMapper.selectStudentByStuNum("2019102995");
        logger.info(student == null ? "空的" : "非空");
    }
}
