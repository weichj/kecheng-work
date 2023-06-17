package com.example.EurekaTrans.Service;

import com.example.EurekaTrans.Bean.Transport;
import com.example.EurekaTrans.Mapper.TransportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportService {

    @Autowired
    private TransportMapper transportMapper;

    public boolean CreateTransport(Transport transport){
        return transportMapper.CreateTransport(transport);
    }

    public List<Transport> SelectAllTransport(){
        return transportMapper.SelectAllTransport();
    }

}
