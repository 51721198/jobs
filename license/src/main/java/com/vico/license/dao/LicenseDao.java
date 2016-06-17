package com.vico.license.dao;

import java.util.List;

import com.vico.license.pojo.LicenseDetail;

public interface LicenseDao {
    int deleteByPrimaryKey(Integer serialNumberId);

    int insert(LicenseDetail record);

    int insertSelective(LicenseDetail record);

    LicenseDetail selectByPrimaryKey(Integer serialNumberId);

    int updateByPrimaryKeySelective(LicenseDetail record);

    int updateByPrimaryKey(LicenseDetail record);
    
    List<LicenseDetail> selectAll();
}