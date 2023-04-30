package com.example.firstWork.Mapper;

import com.example.firstWork.Bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    boolean CreateAccount(String username, String password);

    Account Login(String username,String password);

    boolean DeleteAccount(String username);
}
