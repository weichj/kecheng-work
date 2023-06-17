package com.example.EurekaUser.VueController;

import com.example.EurekaUser.Util.JwtUtil;
import com.example.EurekaUser.Bean.Account;
import com.example.EurekaUser.Service.AccountService;
import com.example.EurekaUser.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    private AccountService accountService;
    @Autowired
    //引入kafka的template
    private KafkaTemplate<String,String> kafkaTemplate;
    //如果是配置类，就注入配置类就可以

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Account u){

        System.out.println("xyz");
        System.out.println(u.toString());
        Account user=accountService.Login(u.getUserName(), u.getPassword());
        Map<String,String> info = new HashMap<>();
        info.put("username",user.getUserName());
        info.put("identity",user.getIdentity());
        String token = JwtUtil.createJWT(UUID.randomUUID().toString(), u.getUserName(), null);
        info.put("token",token);
        return  new  Result(200,"sucess",info);


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
