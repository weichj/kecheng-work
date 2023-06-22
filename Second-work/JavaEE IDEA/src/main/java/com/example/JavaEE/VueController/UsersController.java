package com.example.JavaEE.VueController;

import com.example.JavaEE.Bean.Account;
import com.example.JavaEE.Service.AccountService;
import com.example.JavaEE.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    private AccountService accountService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Account temp_account){
        String username = temp_account.getUserName();
        String password = temp_account.getPassword();
        Account account = accountService.Login(username,password);
        if (account!=null){
            Map<String,String> info = new HashMap<>();
            info.put("identity",account.getIdentity());
            info.put("username",account.getUserName());
            if(account.getIdentity() == "admin"){
                return new Result(202,"success,admin login",info);
            }
            return new Result(200,"success,user login",info);
        }else {
            return new Result(400,"error",null);
        }
    }

    //注册
    @PostMapping("/register")
    public Result reg(@RequestBody Account temp_account){
        String username = temp_account.getUserName();
        String password = temp_account.getPassword();
        if (accountService.CreateAccount(username,password)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"error",null);
        }
    }
}
