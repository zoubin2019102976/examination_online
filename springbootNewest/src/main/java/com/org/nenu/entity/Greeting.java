package com.org.nenu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)

/*测试用，可删除*/
public class Greeting {

    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString(){
        return "[id: " +
                id +
                ", content: " +
                content + "]";
    }
}
