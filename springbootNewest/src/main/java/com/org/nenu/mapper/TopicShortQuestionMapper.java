package com.org.nenu.mapper;

import com.org.nenu.entity.TopicShortQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface TopicShortQuestionMapper {
    ArrayList<TopicShortQuestion> getAllTopicShortQuestion();
    ArrayList<TopicShortQuestion> getTopicShortQuestionByPaperId(int id);
    int updateAShortQuestionById(TopicShortQuestion topicShortQuestion);
    int deleteAShortQuestionById(int id);
    int deleteAShortQuestionByPaperId(int paper_id);
    int addAShortQuestion(TopicShortQuestion topicShortQuestion);
}
