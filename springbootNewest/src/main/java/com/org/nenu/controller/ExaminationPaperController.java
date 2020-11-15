package com.org.nenu.controller;


import com.org.nenu.entity.ExaminationPaper;
import com.org.nenu.entity.Specialty;
import com.org.nenu.entity.TopicSelectOne;
import com.org.nenu.mapper.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class ExaminationPaperController {

    private Logger logger = LoggerFactory.getLogger(ExaminationPaperController.class);

    @Autowired
    SpecialtyMapper specialtyMapper;

    @Autowired
    ExaminationMapper examinationMapper;

    @Autowired
    TopicSelectOneMapper topicSelectOneMapper;
    @Autowired
    TopicSelectManyMapper topicSelectManyMapper;
    @Autowired
    TopicJudgeMapper topicJudgeMapper;
    @Autowired
    TopicShortQuestionMapper topicShortQuestionMapper;

    @GetMapping("get_specialities")//获取专业列表
    public ArrayList<Specialty> getSpecialities(){
        ArrayList<Specialty> specialties = specialtyMapper.getAllSpecialties();
        return specialties;
    }

    @GetMapping("create_page")//获取创建试卷表单部分页面
    public ModelAndView getPage(ModelAndView modelAndView){
        modelAndView.setViewName("/teacher_home_include/create_paper");
        return modelAndView;
    }

    @PostMapping("create_examination_paper")//创建一张试卷
    public int createExaminationPaper(@RequestBody ExaminationPaper examinationPaper){
        int success = examinationMapper.insertAExaminationPaper(examinationPaper);
        return success;
    }

    @GetMapping("edit_examination_paper")//获取编辑试卷那部分页面
    public ModelAndView getEditExaminationPaper(ModelAndView modelAndView){
        modelAndView.setViewName("/teacher_home_include/edit_paper");
        return modelAndView;
    }

    @GetMapping("get_all_examination_paper")
    public ArrayList<ExaminationPaper> getAllExaminationPaper(){
        ArrayList<ExaminationPaper> examinationPapers = examinationMapper.getAllExaminationPapers();
        return examinationPapers;
    }

    @GetMapping("delete_a_paper")
    public int deleteAPaper(@RequestParam(value = "id") Integer id){
        int line_topic_select_one = topicSelectOneMapper.deleteASelectOneByPaperId(id);
        int line_topic_select_many = topicSelectManyMapper.deleteASelectManyByPaperId(id);
        int line_topic_judge = topicJudgeMapper.deleteAJudgeByPaperId(id);
        int line_topic_short = topicShortQuestionMapper.deleteAShortQuestionByPaperId(id);
        int is_success = examinationMapper.deleteAExaminationPaperById(id);
        return is_success;
    }

    @GetMapping("edit_paper_of_topic")
    public ModelAndView getEditPaperOfTopic(ModelAndView modelAndView){
        modelAndView.setViewName("/teacher_home_include/edit_paper_of_topic");
        return modelAndView;
    }

    @GetMapping("get_edit_paper_of_select_edit_form")
    public ModelAndView getSelectEditForm(ModelAndView modelAndView){
        modelAndView.setViewName("/teacher_home_include/edit_paper_of_select_edit_form");
        return modelAndView;
    }

    @GetMapping("get_edit_paper_of_judge_and_short_edit_form")
    public ModelAndView getJudgeShortForm(ModelAndView modelAndView){
        modelAndView.setViewName("/teacher_home_include/edit_paper_of_judge_and_short_edit_form");
        return modelAndView;
    }
}
