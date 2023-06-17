package com.example.JavaEE.Mapper;

import com.example.JavaEE.Bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    boolean CreateAccount(String username, String password);

    Account Login(String username,String password);
}
