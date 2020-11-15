package com.org.nenu.controller;

import com.org.nenu.entity.Specialty;
import com.org.nenu.entity.StudentNew;
import com.org.nenu.mapper.SpecialtyMapper;
import com.org.nenu.mapper.StudentMapper;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/registration")
public class Registration {

    @Autowired
    SpecialtyMapper specialtyMapper;

    @Autowired
    StudentMapper studentMapper;

    private Logger logger = LoggerFactory.getLogger(RequestMapping.class);

    @GetMapping
    public String forwardRegistering(Model model){
        model.addAttribute("student", new StudentNew());
        ArrayList<Specialty> specialties = specialtyMapper.getAllSpecialties();
        model.addAttribute("specialties", specialties);
        return "registration";
    }

    @PostMapping
    public String toRegister(@Valid @ModelAttribute("student") StudentNew studentNew, Errors errors, Model model, HttpSession session,
            RedirectAttributesModelMap redirectAttributesModelMap){
        if (errors.hasErrors()){
            /*model.addAttribute("student", new StudentNew());
            ArrayList<Specialty> specialties = specialtyMapper.getAllSpecialties();
            model.addAttribute("specialties", specialties);*/
            /*return "registration"; //如果以重定向的方式回去，erros中的信息就没了*/
            /*logger.info(errors.toString());*/
            return "redirect:/registration";
        }

        StudentNew studentNew1 = studentMapper.getStudentByAccountNumber(studentNew.getAccount_number());

        if (studentNew1 != null){
            logger.info("注册失败，用户已存在");
            redirectAttributesModelMap.addFlashAttribute("FAIL_USER_EXIST", "注册失败，用户已存在"); //重定向数据发送
            return "redirect:/registration";
        }

        int n = studentMapper.insertStudent(studentNew);
        //redirectAttributesModelMap.addFlashAttribute("SUCCESS", "注册成功"); //使用这种方式，在重定向后的页面获取数据
        session.setAttribute("STUDENT", studentNew);
        return "redirect:/student_home_page";
    }
}
