package com.org.nenu.mapper;

import com.org.nenu.entity.ExaminationPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface ExaminationMapper {
    ArrayList<ExaminationPaper> getAllExaminationPapers();
    ArrayList<ExaminationPaper> getExaminationPapersBySpecialty(String specialty);
    int insertAExaminationPaper(ExaminationPaper examinationPaper);
    int deleteAExaminationPaperById(Integer id);
}
