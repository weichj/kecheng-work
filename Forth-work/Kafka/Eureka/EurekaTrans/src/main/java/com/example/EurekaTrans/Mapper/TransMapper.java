package com.example.EurekaTrans.Mapper;

import com.example.EurekaTrans.Bean.Trans;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TransMapper {
    boolean CreateTrans(Trans trans);

    List<Trans> SelectAllTrans();

    boolean UpdateTrans(Trans trans);

    boolean DeleteTrans(int transOrderId);
}
