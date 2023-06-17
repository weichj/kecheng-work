package com.example.EurekaTrans.VueController;

import com.example.EurekaTrans.Bean.Transport;
import com.example.EurekaTrans.Service.TransportService;
import com.example.EurekaTrans.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("transport")
public class TransportController {

    @Autowired
    private TransportService transportService;

    //添加运输员
    @PostMapping
    public Result CreateTransport(@RequestBody Transport transport){
        if (transportService.CreateTransport(transport)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"success",null);
        }
    }
    @GetMapping
    public Result SelectAllTransport(){
        final List<Transport> transport = transportService.SelectAllTransport();
        return new Result(200,"success",transport);
    }
}
