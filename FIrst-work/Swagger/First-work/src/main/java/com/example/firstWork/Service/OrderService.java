package com.example.firstWork.Service;

import com.example.firstWork.Bean.Order;
import com.example.firstWork.Mapper.OrderMapper;
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

    public Order SelectOrder(int Num){return orderMapper.SelectOrder(Num);}

    public boolean UpdateOrder(Order order){
        return orderMapper.UpdateOrder(order);
    }
    public boolean DeleteOrder(int Num){return orderMapper.DeleteOrder(Num);}

    public List<Order> FindUndisposed(){return orderMapper.FindUndisposed();}

    public List<Order> FindState(){
        return orderMapper.FindState();
    }
}
