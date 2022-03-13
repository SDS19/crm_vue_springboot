package com.crm.settings.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crm.settings.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

    @Select("SELECT * FROM t_user WHERE loginAct = #{loginAct} AND loginPwd = #{loginPwd}")
    User login(User user);

    @Select("SELECT * FROM t_user ORDER BY createTime DESC")
    List<User> owner();
}
