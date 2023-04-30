package com.example.firstWork.Controller;

import com.example.firstWork.Bean.Account;
import com.example.firstWork.Bean.Order;
import com.example.firstWork.Service.AccountService;
import com.example.firstWork.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PageController {
    @Autowired
    public AccountService accountService;
    @Autowired
    public OrderService orderService;
    @PostMapping (value ="/OrderPage")
    public ModelAndView Login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account flag=accountService.Login(username,password);
        if (flag!=null) {
            // 如果用户名和密码正确，创建一个 session，并将用户信息存储在 session 中
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("identity", flag.getIdentity());


//            // 生成一个 UUID 作为 token，并将其存储在 cookie 中
//            String token = UUID.randomUUID().toString();
//            Cookie cookie = new Cookie("token", token);
//            cookie.setMaxAge(3600); // 设置 cookie 的过期时间为 1 小时
//            response.addCookie(cookie);
        }
        ModelAndView mv;
        if (flag!=null){
            if(flag.Identity.equals("admin")){
                mv = new ModelAndView("/adminOrder");
                List<Order> Order = orderService.SelectAllOrder();
                mv.addObject("Order",Order);
                return mv;
            }
            mv = new ModelAndView("/Order");
            List<Order> Order = orderService.FindAllOrder(username);
            mv.addObject("Order",Order);
            return mv;
        }else {
            mv = new ModelAndView("/Login");
            return mv;
        }
    }

    @GetMapping("/OrderPage")
    public ModelAndView OrderPage(HttpServletRequest request, HttpServletResponse response){
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
        HttpSession session = request.getSession(false);
        ModelAndView mv;
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                String identity = (String) session.getAttribute("identity");

                List<Order> Order;
                if(identity.equals("admin")){
                    mv = new ModelAndView("/adminOrder");
                    Order = orderService.SelectAllOrder();
                }else {
                    mv = new ModelAndView("/Order");
                    Order = orderService.FindAllOrder(username);
                }
                mv.addObject("Order",Order);
                return mv;
            }else {
                mv = new ModelAndView("/Login");
                return mv;
            }
        }else {
            mv = new ModelAndView("/Login");
            return mv;
        }

//        if (token != null) {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                String username = (String) session.getAttribute("username");
//                if (username != null) {
//                    String identity = (String) session.getAttribute("identity");
//                    ModelAndView mv;
//                    List<Order> Order;
//                    if(identity.equals("admin")){
//                        mv = new ModelAndView("/adminOrder");
//                        Order = orderService.SelectAllOrder();
//                    }else {
//                        mv = new ModelAndView("/Order");
//                        Order = orderService.FindAllOrder(username);
//                    }
//                    mv.addObject("Order",Order);
//                    return mv;
//                }
//            }
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }else {
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }
    }

    //注册界面
    @GetMapping(value = "/RegisterPage")
    public ModelAndView Reg() {
        ModelAndView mv = new ModelAndView("/Register");
        return mv;
    }

    @GetMapping(value = "/CreateOrderPage")
    public ModelAndView NewOrder(HttpServletRequest request, HttpServletResponse response) {
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
        ModelAndView mv;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                String identity = (String) session.getAttribute("identity");
                if(identity.equals("admin")){
                    mv = new ModelAndView("/adminCreateOrder");
                }else{
                    mv = new ModelAndView("/CreateOrder");
                }
            }
            else{
                mv = new ModelAndView("/Login");
            }
        }else {
            mv = new ModelAndView("/Login");
        }
        return mv;

//        if (token != null) {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                String username = (String) session.getAttribute("username");
//                if (username != null) {
//                    ModelAndView mv = new ModelAndView("/CreateOrder");
//                    return mv;
//                }
//            }
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }else {
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }

    }

    @GetMapping(value = "/SelectOrderPage")
    public ModelAndView SelectOrder(HttpServletRequest request, HttpServletResponse response) {
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
        ModelAndView mv;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {

                mv = new ModelAndView("/SelectOrder");
                return mv;
            }else {
                mv = new ModelAndView("/Login");
                return mv;
            }
        }else {
            mv = new ModelAndView("/Login");
            return mv;
        }

//        if (token != null) {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                String username = (String) session.getAttribute("username");
//                if (username != null) {
//
//                    ModelAndView mv = new ModelAndView("/SelectOrder");
//                    return mv;
//                }
//            }
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }else {
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }


    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/Login");
        return mv;
    }
    @GetMapping("/UpdatePage/{Num}")
    public ModelAndView updatePage(@PathVariable int Num,HttpServletRequest request, HttpServletResponse response){
//        String token = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    token = cookie.getValue();
//                    break;
//                }
//            }
//        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                Order order = orderService.SelectOrder(Num);
                ModelAndView modelAndView =new ModelAndView("/UpdatePage");
                modelAndView.addObject("order",order);
                return modelAndView;
            }else {
                ModelAndView mv = new ModelAndView("/Login");
                return mv;
            }
        }else {
            ModelAndView mv = new ModelAndView("/Login");
            return mv;
        }

//        if (token != null) {
//            HttpSession session = request.getSession(false);
//            if (session != null) {
//                String username = (String) session.getAttribute("username");
//                if (username != null) {
//                    Order order = orderService.SelectOrder(Num);
//                    ModelAndView modelAndView =new ModelAndView("UpdatePage");
//                    modelAndView.addObject("order",order);
//                    return modelAndView;
//                }
//            }
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }else {
//            ModelAndView mv = new ModelAndView("/Login");
//            return mv;
//        }

    }

    @GetMapping("/Logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. 从 request 中获取 HttpSession 对象
        HttpSession session = request.getSession(false);

        // 2. 将 session 无效化，以销毁 session 中保存的数据
        if (session != null) {
            session.invalidate();
        }

        // 3. 创建 Cookie 对象，并将 cookie 的过期时间设置为 0
        Cookie cookie = new Cookie("user", "");
        cookie.setMaxAge(0);

        // 4. 将 Cookie 添加到 response 中，通知客户端删除 cookie
        response.addCookie(cookie);

        // 5. 重定向到登录页面
        ModelAndView mv = new ModelAndView("/Login");
        return mv;
    }
}
