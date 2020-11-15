package com.org.nenu.test;

import com.org.nenu.entity.Specialty;
import com.org.nenu.mapper.SpecialtyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpecialtyGetTest {
    @Autowired
    SpecialtyMapper specialtyMapper;

    private Logger logger = LoggerFactory.getLogger(SpecialtyGetTest.class);

    @Test
    public void getAllSpecialty(){
        ArrayList<Specialty> specialties = specialtyMapper.getAllSpecialties();
        for (Iterator<Specialty> iterator = specialties.iterator(); iterator.hasNext(); ){
            Specialty specialty = (Specialty) iterator.next();
            logger.info(specialty.getSpecialty());
        }
    }
}
