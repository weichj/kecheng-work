package com.example.firstWork.Controller;
import com.example.firstWork.Bean.Order;
import com.example.firstWork.Service.AccountService;
import com.example.firstWork.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
public class Controller {
    @Autowired
    public AccountService accountService;
    @Autowired
    public OrderService orderService;
    @PostMapping(value = "/CreateAccount")
    public ModelAndView shipperAccount(String username,String password) {
        if (accountService.CreateAccount(username, password)){
            return new ModelAndView("/Login");
        }else {
            return new ModelAndView("Error");
        }
    }

    @PostMapping(value = "/CreateOrder")
    public ModelAndView order(Order order,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(order.getShipper()==""){
            order.setShipper((String) session.getAttribute("username"));
        }
        if(order.getTransporter()=="" || order.getTransporter()==null){
            order.setTransporter("未分配");
        }
        if (orderService.CreateOrder(order)){
            return new ModelAndView("/Success");
        }else {
            return new ModelAndView("Error");
        }
    }
    @PostMapping(value = "/SelectOrder")
    public ModelAndView SelectOrder(Integer Num, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String identity = (String) session.getAttribute("identity");
        ModelAndView mv = new ModelAndView("/ShowSelectOrder");
        Order order = orderService.SelectOrder(Num);
        if(identity.equals("admin")){
            mv.addObject("Order",order);
        }else {
            String username = (String) session.getAttribute("username");
            if (username.equals(order.getShipper())){
                mv.addObject("Order",order);
            }else {

            }
        }
        return mv;
    }
    @PostMapping(value = "/UpdateOrder")
    public ModelAndView UpdateOrder(Order order) {
        if (orderService.UpdateOrder(order)){
            return new ModelAndView("/Success");
        }else {
            return new ModelAndView("Error");
        }
    }
    @GetMapping(value = "/DeleteOrder/{Num}")
    public ModelAndView DeleteOrder(@PathVariable("Num") int Num){
        if (orderService.DeleteOrder(Num)){
            return new ModelAndView("/Success");
        }else {
            return new ModelAndView("Error");
        }
    }

    @GetMapping(value = "/UndisposedOrder")
    public ModelAndView Undisposed(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String identity = (String) session.getAttribute("identity");
        ModelAndView mv = new ModelAndView("/adminOrder");
        List<Order> order = orderService.FindUndisposed();
        if(identity.equals("admin")){
            mv.addObject("Order",order);
        }
        return mv;
    }

}
