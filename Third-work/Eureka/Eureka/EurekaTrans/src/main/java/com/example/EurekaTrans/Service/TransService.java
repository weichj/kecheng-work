package com.example.EurekaTrans.Service;

import com.example.EurekaTrans.Bean.Trans;
import com.example.EurekaTrans.Mapper.TransMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransService {
    @Autowired
    private TransMapper transMapper;

    public boolean CreateTrans(Trans trans){
        return transMapper.CreateTrans(trans);
    }

    public List<Trans> SelectAllTrans(){
        return transMapper.SelectAllTrans();
    }

    public boolean UpdateTrans(Trans trans){
        return transMapper.UpdateTrans(trans);
    }

    public boolean DeleteTrans(int transOrderId){
        return transMapper.DeleteTrans(transOrderId);
    }
}
