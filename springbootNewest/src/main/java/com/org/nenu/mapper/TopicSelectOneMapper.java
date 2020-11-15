package com.org.nenu.mapper;

import com.org.nenu.entity.TopicSelectOne;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface TopicSelectOneMapper {
    ArrayList<TopicSelectOne> getTopicSelectOneByPaperId(int id);
    ArrayList<TopicSelectOne> getAllTopicSelectOne();
    int updateASelectOneById(TopicSelectOne topicSelectOne);
    int deleteASelectOneById(int id);
    int addASelectOne(TopicSelectOne topicSelectOne);
    int deleteASelectOneByPaperId(int paper_id);
}
