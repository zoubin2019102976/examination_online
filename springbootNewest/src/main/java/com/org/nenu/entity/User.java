package com.org.nenu.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Component
public class User {

    @Pattern(regexp = "^[0-9]{10,20}$", message = "请正确输入用户账号")
    private String account_number;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
            message = "请填写正确格式的邮箱,例如: 123456@xxx.com")
    private String e_mail;

    @Pattern(regexp = "^[0-9]{11}$", message = "请填写个人手机号")
    private String phone_number;

    @NotNull
    @Size(min = 8, message = "密码必须填写，且不得小于8个字符")
    private String password;


    private String role;
}
