package com.org.nenu.test;

import com.org.nenu.mapper.ExaminationMapper;
import com.org.nenu.pojo.Examination;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExamSelectBySpe {

    @Autowired
    ExaminationMapper examinationMapper;

    @Test
    public void getExam(){
        /*List<Examination> examinations = examinationMapper.selectExamBySpecialty("软件工程");
        Timestamp timestampEnd = Timestamp.valueOf(examinations.get(0).getEndTime().toString());
        Timestamp timestampStart = Timestamp.valueOf(examinations.get(0).getStartTime().toString());
        log.info(String.valueOf(timestampStart.toLocalDateTime()));
        log.info(TimeZone.getDefault().toString());*/
    }
}
