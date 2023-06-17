package com.example.EurekaOrder.VueController;
import com.example.EurekaOrder.Bean.Order;
import com.example.EurekaOrder.Service.OrderService;
import com.example.EurekaOrder.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    //查询当前登录的用户（shipper）名下的订单
    @GetMapping("/{shipper}")
    public Result SelectUserOrder(@PathVariable("shipper")String shipper){
        System.out.println(shipper);
        final List<Order> orders = orderService.FindAllOrder(shipper);
        return new Result(200,"success",orders);
    }
    @GetMapping
    public Result SelectAllOrder(){
        final List<Order> orders = orderService.SelectAllOrder();
        return new Result(200,"success",orders);
    }

    //创建新订单
    @PostMapping
    public Result CreateOrder(@RequestBody Order order){
        if (orderService.CreateOrder(order)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"success",null);
        }
    }

    //删除已有的订单
    @DeleteMapping("/{Num}")
    @ResponseBody
    public Result DeleteOrder(@PathVariable("Num") int Num){
        if(orderService.DeleteOrder(Num)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"error",null);
        }
    }

    //修改已有的订单
    @PutMapping
    public Result UpdateOrder(@RequestBody Order order){
        if (orderService.UpdateOrder(order)){
            return new Result(200,"success",null);
        }else {
            return new Result(400,"error",null);
        }
    }

    //查询已有的订单
    @GetMapping("/Select/{Num}")
    @ResponseBody
    public Result SelectOrder(@PathVariable("Num") int Num) {
        Order order=orderService.SelectOrder(Num);
        if (order != null) {
            return new Result(200, "success", order);
        } else {
            return new Result(400, "error", null);
        }
    }
    @GetMapping("/State")
    @ResponseBody
    public Result State() {
        final List<Order> orders = orderService.FindState();
        return new Result(200,"success",orders);
    }
}
