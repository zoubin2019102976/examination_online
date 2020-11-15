package com.org.nenu.controller;

import com.org.nenu.mapper.StuMapper;
import com.org.nenu.pojo.LoginInfo;
import com.org.nenu.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")

/*无用*/
public class LoginController { //注册

    @Autowired
    StuMapper stuMapper;

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping
    public String processLogin(@Valid @ModelAttribute("studentForRegister") Student studentForRegister, Errors errors, Model model){
        if (errors.hasErrors()){
            return "login";
        }
        Student student = stuMapper.selectStudentByStuNum(studentForRegister.getStuNum());
        if (student != null) {
            model.addAttribute("FAIL", "注册失败，该学号已存在");
            return "login";
        }

        int num = stuMapper.insertToStu(studentForRegister); //返回的是操作成功的记录数
        model.addAttribute("SUCCESS", "注册成功");

        return "redirect:/";
    }

    @GetMapping
    public String forwardLogin(Model model){ //跳转至注册页面
        model.addAttribute("studentForRegister", new Student());
        return "login";
    }
}
