package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TopicSelectMany {
    private Integer id;
    private String paper_name;
    private Integer paper_id;
    private String topic_type = "topic_select_many";
    private Integer topic_num;
    private String description;
    private String itemA;
    private String itemB;
    private String itemC;
    private String itemD;
    private String answer; //A, B, C, D
    private Double grade_per;
    private Boolean flag = Boolean.FALSE;
}
