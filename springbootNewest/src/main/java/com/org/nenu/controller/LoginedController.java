package com.org.nenu.controller;

import com.org.nenu.mapper.ExaminationMapper;
import com.org.nenu.pojo.Examination;
import com.org.nenu.pojo.Student;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("home")
@Slf4j

/*无用*/
public class LoginedController {

    @Autowired
    ExaminationMapper examinationMapper;

    @GetMapping
    public String gotoHome(HttpSession session, Model model){
        //log.info(model.getAttribute("student").toString());
        //log.info(somePro.getPageSize() + " ");
        /*Student student = (Student) session.getAttribute("student");
        String specialty = student.getStuSpecialities().trim();
        List<Examination> exams = examinationMapper.selectExamBySpecialty(student.getStuSpecialities().trim());
        if (!exams.isEmpty()){
            model.addAttribute("exams", exams);
        }
        model.addAttribute("numOfExam", exams.size());*/
        return "home";
    }
}
