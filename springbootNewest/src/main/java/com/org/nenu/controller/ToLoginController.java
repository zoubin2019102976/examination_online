package com.org.nenu.controller;

import com.org.nenu.entity.Administrator;
import com.org.nenu.entity.LoginInformation;
import com.org.nenu.entity.StudentNew;
import com.org.nenu.entity.TeacherNew;
import com.org.nenu.mapper.AdministratorMapper;
import com.org.nenu.mapper.StuMapper;
import com.org.nenu.mapper.StudentMapper;
import com.org.nenu.mapper.TeacherMapper;
import com.org.nenu.pojo.LoginInfo;
import com.org.nenu.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ToLoginController { //登录，首页

    Logger logger = LoggerFactory.getLogger(ToLoginController.class);

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    AdministratorMapper administratorMapper;

    @GetMapping
    public String toIndex(Model model){
        model.addAttribute("loginInfo", new LoginInformation());
        return "index";
    }

    @PostMapping("toLogin")
    public String toLogin(@Valid @ModelAttribute("loginInfo") LoginInformation loginInfo,
                          Errors errors,
                          Model model,
                          HttpSession session,
                          RedirectAttributesModelMap redirectAttributesModelMap){
        if (errors.hasErrors()){
            return "index";
        }
        String account_num = loginInfo.getAccount_number();
        String password = loginInfo.getPassword();
        String role = loginInfo.getRole();

        char R = role.equals("student") ? 'S' : (role.equals("teacher") ? 'T' : 'A');

        switch (R){
            case 'S':
                StudentNew studentNew = studentMapper.getStudentByAccountNumber(account_num);
                if (studentNew == null){
                    redirectAttributesModelMap.addFlashAttribute("USER_NOT_EXIST", "登录失败,账户不存在");
                }else if(!password.equals(studentNew.getPassword())){
                    redirectAttributesModelMap.addFlashAttribute("WRONG_PASSWORD", "密码错误");
                }else {

                    session.setAttribute("STUDENT", studentNew);
                    return "redirect:/student_home_page";
                }
                break;
            case 'T':
                TeacherNew teacherNew = teacherMapper.getTeacherByAccountNumber(account_num);
                if (teacherNew == null){
                    redirectAttributesModelMap.addFlashAttribute("USER_NOT_EXIST", "登录失败,账户不存在");
                }else if (!password.equals(teacherNew.getPassword())){
                    redirectAttributesModelMap.addFlashAttribute("WRONG_PASSWORD", "密码错误");
                }else {
                    session.setAttribute("TEACHER", teacherNew);
                    return "redirect:/teacher_home_page";
                }
                break;
            case 'A':
                Administrator administrator = administratorMapper.getAdministratorByAccountNumber(account_num);
                if (administrator == null){
                    redirectAttributesModelMap.addFlashAttribute("USER_NOT_EXIST", "登录失败,账户不存在");
                }else if(!password.equals(administrator.getPassword())) {
                    redirectAttributesModelMap.addFlashAttribute("WRONG_PASSWORD", "密码错误");
                }else {
                    return "redirect:/administrator_home_page";
                }
                break;
            default:
                return "redirect:/";
        }

        return "redirect:/";
    }
}
