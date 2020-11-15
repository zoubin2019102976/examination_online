package com.org.nenu.test;

import com.org.nenu.entity.StudentNew;
import com.org.nenu.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EqTest{

    private Logger logger = LoggerFactory.getLogger(EqTest.class);

    @Test
    public void myeqTest(){
        String m1 = "1111";
        StudentNew studentNew = new StudentNew();
        studentNew.setPassword("1111");
        logger.info(studentNew.getPassword().equals(m1)+"");
    }
}
