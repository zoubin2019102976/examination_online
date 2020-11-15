package com.org.nenu.mapper;


import com.org.nenu.entity.TopicJudge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface TopicJudgeMapper {
    ArrayList<TopicJudge> getAllTopicJudge();
    ArrayList<TopicJudge> getTopicJudgeByPaperId(int id);
    int updateAJudgeById(TopicJudge topicJudge);
    int deleteAJudgeById(int id);
    int deleteAJudgeByPaperId(int paper_id);
    int addAJudge(TopicJudge topicJudge);
}
