package com.org.nenu.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Component
@Alias("Student")
public class Student {
    private Integer stuId; //学生id

    @NotBlank(message = "姓名不能为空")
    private String stuName; //学生姓名

    @Pattern(regexp = "^[0-9]{10}$", message = "请正确输入学生学号:10位数字")
    private String stuNum; //学生学号

    @NotBlank(message = "专业不允许为空")
    private String stuSpecialities; //学生专业

    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
            message = "请填写正确格式的邮箱,例如: 123456@xxx.com")
    private String stuEmail; //学生E-mail

    @Pattern(regexp = "^[0-9]{11}$", message = "请填写个人手机号")
    private String stuPhone; //学生电话号

    @NotNull
    @Size(min = 8, message = "密码必须填写，且不得小于8个字符")
    private String password; //登录密码

    private String stuRole = "student";
}
