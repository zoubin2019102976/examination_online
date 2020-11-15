package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TopicJudge {
    private Integer id;
    private Integer paper_id;
    private String paper_name;
    private String topic_type = "topic_judge";
    private Integer topic_num;
    private String description;
    private String answer; // YES or NO
    private Double grade_per;
    private Boolean flag = Boolean.FALSE;
}
