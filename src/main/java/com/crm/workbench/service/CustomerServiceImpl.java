package com.crm.workbench.service;

import com.crm.exceptions.DaoException;
import com.crm.settings.dao.UserDao;
import com.crm.vo.Pagination;
import com.crm.workbench.dao.CustomerDao;
import com.crm.workbench.domain.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;
    @Resource
    private UserDao userDao;

    @Override
    public Pagination<Customer> customerList(Customer customer) throws DaoException {
        int total = customerDao.total(customer);
        List<Customer> list = customerDao.customerList(customer);
        if (total!=0 && list==null) throw new DaoException("Customers query failed!");
        return new Pagination<>(total,list);
    }

    @Override
    public void create(Customer customer) throws DaoException {
        if (customerDao.insert(customer)!=1) throw new DaoException("Create customer failed!");
    }

    @Override
    public Map<String, Object> edit(String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("owner",userDao.owner());
        map.put("customer",customerDao.edit(id));
        return map;
    }

    @Override
    public List<String> autoComplete(String name) {
        return customerDao.selectName(name);
    }

    @Override
    public void update(Customer customer) throws DaoException{
        if (customerDao.update(customer)!=1) throw new DaoException("Update customer failed!");
    }

}
