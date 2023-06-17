package com.example.EurekaTrans.Mapper;

import com.example.EurekaTrans.Bean.Transport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TransportMapper {

    boolean CreateTransport(Transport transport);

    List<Transport> SelectAllTransport();
}
