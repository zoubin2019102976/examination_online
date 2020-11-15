package com.org.nenu.controller;

import com.org.nenu.entity.TopicShortQuestion;
import com.org.nenu.mapper.TopicShortQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TopicShortQuestionController {
    @Autowired
    TopicShortQuestionMapper topicShortQuestionMapper;

    @GetMapping("get_topic_short_by_paper_id")
    public ArrayList<TopicShortQuestion> getTopicJudgeByPaperId(@RequestParam(value = "id") Integer id){
        ArrayList<TopicShortQuestion> topicShortQuestions = topicShortQuestionMapper.getTopicShortQuestionByPaperId(id);
        return topicShortQuestions;
    }

    @PostMapping("add_a_topic_short")
    public int addATopicShort(@RequestBody TopicShortQuestion topicShortQuestion){
        int is_success = topicShortQuestionMapper.addAShortQuestion(topicShortQuestion);
        return is_success;
    }

    @PostMapping("delete_a_topic_short")
    public int deleteATopicShort(@RequestBody TopicShortQuestion topicShortQuestion){
        int is_success = topicShortQuestionMapper.deleteAShortQuestionById(topicShortQuestion.getId());
        return is_success;
    }

    @PostMapping("update_a_topic_short")
    public int updateATopicShort(@RequestBody TopicShortQuestion topicShortQuestion){
        int is_success = topicShortQuestionMapper.updateAShortQuestionById(topicShortQuestion);
        return is_success;
    }
}
