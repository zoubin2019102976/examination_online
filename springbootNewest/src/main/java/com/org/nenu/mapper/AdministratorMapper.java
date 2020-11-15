package com.org.nenu.mapper;

import com.org.nenu.entity.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Mapper
public interface AdministratorMapper {
    Administrator getAdministratorByAccountNumber(String account_number);
    ArrayList<Administrator> getAllAdministrator();
    int insertAdministrator(Administrator administrator);
}
