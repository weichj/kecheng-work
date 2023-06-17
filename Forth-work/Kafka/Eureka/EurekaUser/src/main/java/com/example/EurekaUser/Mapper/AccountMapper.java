package com.example.EurekaUser.Mapper;

import com.example.EurekaUser.Bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
    boolean CreateAccount(String username, String password);

    Account Login(String username, String password);
}
