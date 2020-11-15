package com.org.nenu.controller;

import com.org.nenu.entity.TopicSelectOne;
import com.org.nenu.mapper.TopicSelectOneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TopicSelectOneController {
    @Autowired
    TopicSelectOneMapper topicSelectOneMapper;

    @GetMapping("get_topic_select_one_by_paper_id")
    public ArrayList<TopicSelectOne> getTopicSelectOneByPaperId(@RequestParam(value = "id") Integer id){
        ArrayList<TopicSelectOne> topicSelectOnes = topicSelectOneMapper.getTopicSelectOneByPaperId(id);
        return topicSelectOnes;
    }

    @PostMapping("update_a_topic_select_one")
    public int updateATopicSelectOne(@RequestBody TopicSelectOne topicSelectOne){
        int i = topicSelectOneMapper.updateASelectOneById(topicSelectOne);
        return i;
    }

    @PostMapping("delete_a_topic_select_one")
    public int deleteATopicSelectOne(@RequestBody TopicSelectOne topicSelectOne){
        int is_success = topicSelectOneMapper.deleteASelectOneById(topicSelectOne.getId());
        return is_success;
    }

    @PostMapping("add_a_topic_select_one")
    public int addATopicSelectOne(@RequestBody TopicSelectOne topicSelectOne){
        int i = topicSelectOneMapper.addASelectOne(topicSelectOne);
        return i;
    }
}
