package com.org.nenu.controller;


import com.org.nenu.entity.Greeting;
import com.org.nenu.entity.Specialty;
import com.org.nenu.mapper.SpecialtyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
/*测试用,可删除*/
@RestController
public class GreetingController {
    private Logger logger = LoggerFactory.getLogger(GreetingController.class);

    /*@Autowired
    SpecialtyMapper specialtyMapper;*/

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        Greeting greeting = new Greeting();
        greeting.setContent(String.format(template, name));
        greeting.setId(counter.incrementAndGet());

        return greeting;
    }

    //@CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/greeting")
    public Greeting greeting_post(@RequestBody Greeting greeting){
        logger.info(greeting.toString());
        greeting.setId(counter.incrementAndGet());
        greeting.setContent("This is a alter User");

        return greeting;
    }

    /*@GetMapping("create_page")
    public ModelAndView getPage(ModelAndView modelAndView, Model model){
        modelAndView.setViewName("/teacher_home_include/create_paper");
        return modelAndView;
    }*/

    /*@GetMapping("get_specialities")
    public ArrayList<Specialty> getSpecialities(){
        ArrayList<Specialty> specialties = specialtyMapper.getAllSpecialties();
        return specialties;
    }*/
}
