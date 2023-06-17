package com.example.JavaEE.Service;

import com.example.JavaEE.Bean.Order;
import com.example.JavaEE.Mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public boolean CreateOrder(Order order){
        return orderMapper.CreateOrder(order);
    }
    public List<Order> FindAllOrder(String shipper){
        return orderMapper.FindAllOrder(shipper);
    }
    public List<Order> SelectAllOrder(){
        return orderMapper.SelectAllOrder();
    }

    public Order SelectOrder(int num){return orderMapper.SelectOrder(num);}

    public boolean UpdateOrder(Order order){
        return orderMapper.UpdateOrder(order);
    }
    public boolean DeleteOrder(int num){return orderMapper.DeleteOrder(num);}

    public List<Order> FindState(){
        return orderMapper.FindState();
    }
}
