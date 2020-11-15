package com.org.nenu.controller;

import com.org.nenu.entity.TopicSelectMany;
import com.org.nenu.entity.Value;
import com.org.nenu.mapper.TopicSelectManyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TopicSelectManyController {

    @Autowired
    TopicSelectManyMapper topicSelectManyMapper;

    @GetMapping("get_topic_select_many_by_paper_id")
    public ArrayList<TopicSelectMany> getTopicSelectManyByPaperId(@RequestParam(value = "id") Integer id){
        ArrayList<TopicSelectMany> topicSelectManies = topicSelectManyMapper.getTopicSelectManyByPaperId(id);
        return topicSelectManies;
    }

    @PostMapping("add_a_topic_select_many")
    public int addATopicSelectOne(@RequestBody TopicSelectMany topicSelectMany){
        int i = topicSelectManyMapper.addASelectMany(topicSelectMany);
        return i;
    }

    @PostMapping("update_a_topic_select_many")
    public int updateATopicSelectMany(@RequestBody TopicSelectMany topicSelectMany){
        int i = topicSelectManyMapper.updateASelectManyById(topicSelectMany);
        return i;
    }

    @PostMapping("delete_a_topic_select_many")
    public int deleteATopicSelectMany(@RequestBody TopicSelectMany topicSelectMany){
        int i = topicSelectManyMapper.deleteASelectManyById(topicSelectMany.getId());
        return i;
    }
}
