package com.vico.license.dao;

import java.util.List;

import com.vico.license.pojo.Hospital;

public interface HospitalDao {
    int deleteByPrimaryKey(Integer hospitalNumber);

    int insert(Hospital record);

    int insertSelective(Hospital record);

    Hospital selectByPrimaryKey(Integer hospitalNumber);

    int updateByPrimaryKeySelective(Hospital record);

    int updateByPrimaryKey(Hospital record);
    
    List<Hospital> showAll();
    
}