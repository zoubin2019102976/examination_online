package com.org.nenu.test;

import com.org.nenu.entity.StudentNew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestExtendData {

    private Logger logger = LoggerFactory.getLogger(TestExtendData.class);

    @Test
    public void TestEx(){
        StudentNew studentNew = new StudentNew();
        studentNew.setId(1);
        studentNew.setAccount_number("123456");
        studentNew.setPassword("123456");
        studentNew.setRole("Stu");

        logger.info(studentNew.getRole());
    }
}
