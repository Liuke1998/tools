package com.example.tools.mapper;

import com.example.tools.entry.UserFund;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFundMapper {
    //添加关注基金
    void insert(UserFund userFund);
}
