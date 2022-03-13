package com.crm.settings.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crm.exceptions.LoginException;
import com.crm.settings.dao.UserDao;
import com.crm.settings.domain.User;
import com.crm.settings.mapper.UserMapper;
import com.crm.utils.DateTimeUtil;
import com.crm.workbench.dao.ActivityDao;
import com.crm.workbench.domain.Activity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public User login(User user) throws LoginException {
        User result = userDao.login(user);
        if (result==null) throw new LoginException("Account or password wrong!");
        if (DateTimeUtil.expire(result.getExpireTime())) throw new LoginException("Account expire!");
        if ("0".equals(result.getLockState())) throw new LoginException("Account is locked!");
        String allowIps = result.getAllowIps();
        if ("".equals(allowIps) && allowIps!=null && !allowIps.contains(user.getAllowIps())) throw new LoginException("This IP is restricted to login!");
        return result;
    }

    @Override
    public boolean saveUser(User user) {
        return saveOrUpdate(user);
    }

    @Override
    public boolean delete(String id) {
        return removeById(id);
    }

    @Override
    public List<User> owner() {
        return list();
    }
}
