package com.org.nenu.test;

import com.org.nenu.mapper.EmpMapper;
import com.org.nenu.pojo.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmpTest {

    @Autowired
    private EmpMapper empMapper;

    private Logger logger = LoggerFactory.getLogger(EmpTest.class);
    @Test
    public void selectAllTest(){
        List<Emp> list = empMapper.selectAll();
        for (Emp emp: list){
            logger.info(emp.toString());
        }
    }
}
