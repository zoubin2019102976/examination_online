package com.org.nenu.controller;

import com.org.nenu.entity.TopicJudge;
import com.org.nenu.mapper.TopicJudgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TopicJudgeController {
    @Autowired
    TopicJudgeMapper topicJudgeMapper;

    @GetMapping("get_topic_judge_by_paper_id")
    public ArrayList<TopicJudge> getTopicJudgeByPaperId(@RequestParam(value = "id") Integer id){
        ArrayList<TopicJudge> topicJudges = topicJudgeMapper.getTopicJudgeByPaperId(id);
        return topicJudges;
    }

    @PostMapping("add_a_topic_judge")
    public int addATopicJudge(@RequestBody TopicJudge topicJudge){
        int i = topicJudgeMapper.addAJudge(topicJudge);
        return i;
    }

    @PostMapping("delete_a_topic_judge")
    public int deleteATopicJudge(@RequestBody TopicJudge topicJudge){
        int i = topicJudgeMapper.deleteAJudgeById(topicJudge.getId());
        return i;
    }

    @PostMapping("update_a_topic_judge")
    public int updateATopicJudge(@RequestBody TopicJudge topicJudge){
        int i = topicJudgeMapper.updateAJudgeById(topicJudge);
        return i;
    }
}
