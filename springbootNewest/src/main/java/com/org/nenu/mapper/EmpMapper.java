package com.org.nenu.mapper;

import com.org.nenu.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EmpMapper {
    List<Emp> selectAll();
}