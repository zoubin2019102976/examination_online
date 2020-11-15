package com.org.nenu.test;

import com.org.nenu.entity.*;
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
public class TestExamination {

    private Logger logger = LoggerFactory.getLogger(TestExtendData.class);

    private ArrayList<TopicSelectOne> topicSelectOnes = new ArrayList<>();
    private ArrayList<TopicSelectMany> topicSelectManies = new ArrayList<>();
    private ArrayList<TopicJudge> topicJudges = new ArrayList<>();
    private ArrayList<TopicShortQuestion> topicShortQuestions = new ArrayList<>();

    @Autowired
    private TopicSelectOne topicSelectOne;

    @Autowired
    private TopicSelectMany topicSelectMany;

    @Autowired
    private TopicJudge topicJudge;

    @Autowired
    private TopicShortQuestion topicShortQuestion;

    @Autowired
    private ExaminationPaper examinationPaper;

    @Test
    public void testExamination(){
        topicSelectOne.setId(1);
        topicSelectOne.setPaper_name("Math");
        topicSelectOne.setTopic_num(1);

        topicSelectMany.setId(1);
        topicSelectMany.setPaper_name("Chinese");
        topicSelectMany.setTopic_num(2);

        topicJudge.setId(1);
        topicJudge.setPaper_name("UML");
        topicJudge.setTopic_num(3);

        topicShortQuestion.setId(1);
        topicShortQuestion.setPaper_name("Data Struct");
        topicShortQuestion.setTopic_num(4);

        topicSelectOnes.add(topicSelectOne);
        topicSelectManies.add(topicSelectMany);
        topicJudges.add(topicJudge);
        topicShortQuestions.add(topicShortQuestion);

        examinationPaper.setId(5);
        examinationPaper.setSpecialty("Software");
        examinationPaper.setPaper_name("2020/software");

        examinationPaper.setTopic_judge(topicJudges);
        examinationPaper.setTopic_select_one(topicSelectOnes);
        examinationPaper.setTopic_select_many(topicSelectManies);
        examinationPaper.setTopic_short_question(topicShortQuestions);

        logger.info(examinationPaper.getSpecialty());
        logger.info(examinationPaper.getPaper_name());

        for (Iterator<TopicSelectOne> it = topicSelectOnes.iterator(); it.hasNext(); ){
            TopicSelectOne tmp = (TopicSelectOne) it.next();
            logger.info(tmp.getId().toString());
            logger.info(tmp.getPaper_name());
            logger.info(tmp.getTopic_num().toString());
        }

        for (Iterator<TopicSelectMany> it = topicSelectManies.iterator(); it.hasNext(); ){
            TopicSelectMany tmp = (TopicSelectMany) it.next();
            logger.info(tmp.getId().toString());
            logger.info(tmp.getPaper_name());
            logger.info(tmp.getTopic_num().toString());
        }
        for (Iterator<TopicJudge> it = topicJudges.iterator(); it.hasNext(); ){
            TopicJudge tmp = (TopicJudge) it.next();
            logger.info(tmp.getId().toString());
            logger.info(tmp.getPaper_name());
            logger.info(tmp.getTopic_num().toString());
        }
        for (Iterator<TopicShortQuestion> it = topicShortQuestions.iterator(); it.hasNext(); ){
            TopicShortQuestion tmp = (TopicShortQuestion) it.next();
            logger.info(tmp.getId().toString());
            logger.info(tmp.getPaper_name());
            logger.info(tmp.getTopic_num().toString());
        }

    }
}
