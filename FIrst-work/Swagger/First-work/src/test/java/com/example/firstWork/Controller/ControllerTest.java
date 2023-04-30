package com.example.firstWork.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.firstWork.Bean.Order;
import com.example.firstWork.Service.AccountService;
import com.example.firstWork.Service.OrderService;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RunWith(SpringRunner.class)
@SpringBootTest
class ControllerTest {

    @Autowired
    public Controller controller;

    @Autowired
    public OrderService orderService;

    @Autowired
    public AccountService accountService;

    Order order = new Order();




    @Test
    void shipperAccount() {
        ModelAndView mav = controller.shipperAccount("9","9");

        assertEquals("/Login",mav.getViewName());

        boolean flag = accountService.DeleteAccount("9");

        assertEquals(true,flag);

    }

    @Test
    void order() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        // 构造登录请求参数
        request.setParameter("username", "1");

        HttpSession session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        ModelAndView mav = controller.order(order,request);

        assertEquals("/Success",mav.getViewName());

    }

    @Test
    void selectOrder() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "1");

        HttpSession session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        // 发送登录请求
        ModelAndView mav = controller.SelectOrder(1,request);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/ShowSelectOrder", mav.getViewName());
    }

    @Test
    void updateOrder() {
        order.setNum(1);
        order.setShipper("2");
        order.setOrigin("北京");
        order.setDestination("湖南");
        order.setGoodsName("臭豆腐");
        order.setCarriers("圆通");
        order.setState("");

        // 发送登录请求
        ModelAndView mav = controller.UpdateOrder(order);

        // 验证返回的视图名是否正确
        assertEquals("/Success", mav.getViewName());
    }

    @Test
    void deleteOrder() {
        // 调用方法进行删除
        ModelAndView mav = controller.DeleteOrder(7);

        // 验证返回结果
        assertEquals("/Success", mav.getViewName());

    }

    @Test
    void undisposed() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "1");

        HttpSession session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        // 发送登录请求
        ModelAndView mav = controller.Undisposed(request);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/adminOrder", mav.getViewName());
    }
}