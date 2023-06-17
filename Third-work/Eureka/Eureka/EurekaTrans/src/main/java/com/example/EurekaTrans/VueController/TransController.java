package com.example.EurekaTrans.VueController;

import com.example.EurekaTrans.Bean.Trans;
import com.example.EurekaTrans.Service.TransService;
import com.example.EurekaTrans.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("trans")
public class TransController {
    @Autowired
    private TransService transService;

    //添加运输员
    @PostMapping
    public Result CreateTrans(@RequestBody Trans trans){
        if (transService.CreateTrans(trans)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"success",null);
        }
    }
    @GetMapping
    public Result SelectAllTrans(){
        final List<Trans> trans = transService.SelectAllTrans();
        return new Result(200,"success",trans);
    }

    //删除已有的订单
    @DeleteMapping("/{transOrderId}")
    @ResponseBody
    public Result DeleteOrder(@PathVariable("transOrderId") int transOrderId){
        if(transService.DeleteTrans(transOrderId)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"error",null);
        }
    }

    //修改已有的订单
    @PutMapping
    public Result UpdateOrder(@RequestBody Trans trans){
        if (transService.UpdateTrans(trans)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"error",null);
        }
    }
}
