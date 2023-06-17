package com.example.JavaEE.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.example.JavaEE.Bean.Order;
import com.example.JavaEE.Service.OrderService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
class PageControllerTest {


    @Autowired
    public PageController pageController;

    @Autowired
    public OrderService orderService;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(pageController).build();
    }
    @Test
    void login() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "2");
        request.setParameter("password", "2");

        // 发送登录请求
        ModelAndView mav = pageController.Login(request, response);

        // 验证返回的视图名是否正确
        assertEquals("/Order", mav.getViewName());

        // 验证返回的订单列表是否正确
        List<Order> orders = (List<Order>) mav.getModelMap().get("Order");
        assertNotNull(orders);
        assertEquals(3, orders.size());

        request.setParameter("username", "1");
        request.setParameter("password", "1");

        // 发送登录请求
        mav = pageController.Login(request, response);

        // 验证返回的视图名是否正确
        assertEquals("/adminOrder", mav.getViewName());

        // 验证返回的订单列表是否正确
        orders = (List<Order>) mav.getModelMap().get("Order");
        assertNotNull(orders);
        assertEquals(8, orders.size());
    }

    @Test
    void orderPage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "2");

        HttpSession session = request.getSession();
        session.setAttribute("username", "2");
        session.setAttribute("identity", "user");

        // 发送登录请求
        ModelAndView mav = pageController.OrderPage(request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/Order", mav.getViewName());

        // 验证返回的订单列表是否正确
        List<Order> orders = (List<Order>) mav.getModelMap().get("Order");
        assertNotNull(orders);
        assertEquals(3, orders.size());

        request.setParameter("username", "1");

        session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        // 发送登录请求
        mav = pageController.OrderPage(request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/adminOrder", mav.getViewName());

        // 验证返回的订单列表是否正确
        orders = (List<Order>) mav.getModelMap().get("Order");
        assertNotNull(orders);
        assertEquals(8, orders.size());

    }

    @Test
    void reg() {
        ModelAndView mav = pageController.Reg();

        assertEquals("/Register",mav.getViewName());
    }

    @Test
    void newOrder() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "2");

        HttpSession session = request.getSession();
        session.setAttribute("username", "2");
        session.setAttribute("identity", "user");

        // 发送登录请求
        ModelAndView mav = pageController.NewOrder(request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/CreateOrder", mav.getViewName());

        request.setParameter("username", "1");

        session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        // 发送登录请求
        mav = pageController.NewOrder(request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/adminCreateOrder", mav.getViewName());
    }

    @Test
    void selectOrder() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "2");

        HttpSession session = request.getSession();
        session.setAttribute("username", "2");
        session.setAttribute("identity", "user");

        // 发送登录请求
        ModelAndView mav = pageController.SelectOrder(request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/SelectOrder", mav.getViewName());
    }

    @Test
    void index() {
        ModelAndView mav = pageController.index();
        assertEquals("/Login",mav.getViewName());
    }

    @Test
    void updatePage() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 构造登录请求参数
        request.setParameter("username", "1");

        HttpSession session = request.getSession();
        session.setAttribute("username", "1");
        session.setAttribute("identity", "admin");

        Order test = orderService.SelectOrder(3);

        // 发送登录请求
        ModelAndView mav = pageController.updatePage(3,request, response);

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        // 验证返回的视图名是否正确
        assertEquals("/UpdatePage", mav.getViewName());

        Order order = (Order) mav.getModelMap().get("order");
        assertNotNull(order);
        assertEquals(test,order);

    }

    @Test
    void logout() {
    }
}