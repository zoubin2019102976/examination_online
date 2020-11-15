package com.org.nenu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class TestTemplateController {

    @RequestMapping("/test/thymeleaf")
    public String testThymeleaf(ModelMap modelMap, HttpSession session){
        modelMap.addAttribute("attrNameRequestScope", "<p style='color:blue;font-size:100px;'>attrValueRequestScope</p>");

        session.setAttribute("attrNameSessionScope", "attrValueSessionScope");


        modelMap.addAttribute("list", Arrays.asList("AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "HHH"));
        return "hello";
    }
}
