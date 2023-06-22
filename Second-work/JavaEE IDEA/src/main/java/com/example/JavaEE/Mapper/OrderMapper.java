package com.example.JavaEE.Mapper;

import com.example.JavaEE.Bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    boolean CreateOrder(Order order);
    List<Order> FindAllOrder(String Shipper);
    List<Order> SelectAllOrder();
    Order SelectOrder(int Num);
    boolean UpdateOrder(Order order);

    boolean DeleteOrder(int Num);
    List<Order> FindState();
}
