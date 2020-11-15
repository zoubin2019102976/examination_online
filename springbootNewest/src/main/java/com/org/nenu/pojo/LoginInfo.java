package com.org.nenu.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Component
public class LoginInfo {

    @Pattern(regexp = "^[0-9]{10}$", message = "请填写正确格式的学号")
    private String stuNum;

    @NotNull
    @Size(min = 8, message = "密码必须填写，且不能少于8个字符")
    private String password;
}
