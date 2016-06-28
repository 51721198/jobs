package com.vico.license.dao;

import java.util.List;

import com.vico.license.pojo.Hospital;

/**
 * 
 * @ClassName: HospitalDao
 * @Description: 医院信息Dao层
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:45:17
 */
public interface HospitalDao {
    int deleteByPrimaryKey(Integer hospitalNumber);

    int insert(Hospital hospital);

    int insertSelective(Hospital hospital);

    Hospital selectByPrimaryKey(Integer hospitalNumber);

    int updateByPrimaryKeySelective(Hospital hospital);

    int updateByPrimaryKey(Hospital hospital);
    
    List<Hospital> showAll();
}
