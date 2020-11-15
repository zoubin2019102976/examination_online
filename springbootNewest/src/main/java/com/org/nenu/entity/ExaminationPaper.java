package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@Component
public class ExaminationPaper {
    private Integer id;
    private String specialty;
    private String paper_name;
    private String teacher;
    private Timestamp start_time;
    private Timestamp end_time;
    private Double full_marks;
    private ArrayList<TopicSelectOne> topic_select_one;
    private ArrayList<TopicSelectMany> topic_select_many;
    private ArrayList<TopicJudge> topic_judge;
    private ArrayList<TopicShortQuestion> topic_short_question;
}
