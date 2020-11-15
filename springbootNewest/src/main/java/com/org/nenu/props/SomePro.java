package com.org.nenu.props;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
public class SomePro {
    private int pageSize = 200;
}
