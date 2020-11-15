package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Component
public class LoginInformation {
    @Pattern(regexp = "^[0-9]{10,20}$", message = "请正确输入用户账号")
    private String account_number;

    @NotNull
    @Size(min = 8, message = "密码必须填写，且不能少于8个字符")
    private String password;

    @NotNull(message = "必须选择一个角色登录")
    private String role;
}
