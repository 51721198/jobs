package com.vico.license.dao;

import java.util.List;

import com.vico.license.pojo.LicenseDetail;

/**
 * 
 * @ClassName: LicenseDao
 * @Description: 序列号信息Dao层
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:46:25
 */
public interface LicenseDao {
    int deleteByPrimaryKey(Integer serialNumberId);

    int insert(LicenseDetail record);

    LicenseDetail selectByPrimaryKey(Integer serialNumberId);

    int updateByPrimaryKey(LicenseDetail record);
    
    List<LicenseDetail> selectAllLicenses();
    
    List<LicenseDetail> selectByhospitalNumber(int hospitalNumber);
}