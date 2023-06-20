package com.example.EurekaOrder.Service;

import com.example.EurekaOrder.Bean.Order;
import com.example.EurekaOrder.Mapper.OrderMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public boolean CreateOrder(Order order) {
        return orderMapper.CreateOrder(order);
    }

    public List<Order> FindAllOrder(String shipper) {
        return orderMapper.FindAllOrder(shipper);
    }

    public List<Order> SelectAllOrder() {
        return orderMapper.SelectAllOrder();
    }

    @HystrixCommand(fallbackMethod = "getFallbackOrder")
    public Order SelectOrder(int Num) throws Exception{
        if(Num<=0){
            throw new Exception();
        }
        return orderMapper.SelectOrder(Num);
    }
    public Order getFallbackOrder(int Num) {
        Order order = new Order();
        order.setNum(-1);
        order.setShipper("error");
        order.setOrigin("error");
        order.setDestination("error");
        order.setGoodsName("error");
        order.setCarriers("error");
        order.setState("error");
        System.out.println("In fallbackOrder function");
        return order;
    }

    public boolean UpdateOrder(Order order) {
        return orderMapper.UpdateOrder(order);
    }

    public boolean DeleteOrder(int Num) {
        return orderMapper.DeleteOrder(Num);
    }

    public List<Order> FindState() {
        return orderMapper.FindState();
    }

}
