package com.org.nenu.mapper;

import com.org.nenu.entity.TopicSelectMany;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface TopicSelectManyMapper {
    ArrayList<TopicSelectMany> getAllTopicSelectMany();
    ArrayList<TopicSelectMany> getTopicSelectManyByPaperId(int id);
    int updateASelectManyById(TopicSelectMany topicSelectMany);
    int deleteASelectManyById(int id);
    int deleteASelectManyByPaperId(int paper_id);
    int addASelectMany(TopicSelectMany topicSelectMany);
}
