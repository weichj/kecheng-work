package com.example.firstWork.Service;

import com.example.firstWork.Bean.Account;
import com.example.firstWork.Mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;
    public boolean CreateAccount(String username, String password){
        return accountMapper.CreateAccount(username, password);
    }
    public Account Login(String username,String password){
        return accountMapper.Login(username,password);
    }

    public boolean DeleteAccount(String username){return accountMapper.DeleteAccount(username);}

}
