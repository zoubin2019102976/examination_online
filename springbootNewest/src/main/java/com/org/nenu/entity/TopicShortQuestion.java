package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TopicShortQuestion {
    private Integer id;
    private String paper_name;
    private Integer paper_id;
    private String topic_type = "topic_short_question";
    private Integer topic_num;
    private String description;
    private String answer;
    private Double grade_per;
    private Boolean flag = Boolean.FALSE;
}
