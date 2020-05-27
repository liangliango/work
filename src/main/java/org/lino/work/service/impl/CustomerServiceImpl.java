package org.lino.work.service.impl;

import org.lino.work.base.bean.*;
import org.lino.work.iobus.dao.ICustomerClearDao;
import org.lino.work.iobus.dao.ICustomerDao;
import org.lino.work.iobus.dao.IUserDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private static final String kh = "KH";

    @Autowired
    private ICustomerDao customerDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IWayBillDao wayBillDao;

    @Autowired
    private ICustomerClearDao customerClearDao;

    @Override
    public boolean save(Customer customer) {

        String id = kh+(int)Math.random()*100000;
        Customer customer1 = customerDao.findByCustomerId(id);
        while(customer1 != null){
            id = kh+(int)Math.random()*100000;
            customer1 = customerDao.findByCustomerId(id);
        }
        customer.setCustomerId(id);
        try {
            customerDao.save(customer);
            User user = new User();
            user.setPassword("123456");
            user.setLoginId(customer.getCustomerId());
            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String customerCode) {
        try {
            customerDao.deleteByCustomerId(customerCode);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(String customerCode, Customer customer) {
        try {
            customerDao.save(customer);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<Customer> selectAllCusByPage(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    public Customer selectByCustomerCode(String customerCode) {
        return customerDao.findByCustomerId(customerCode);
    }

    @Override
    public List<String> selectAllCusCode() {
        return customerDao.findAllCustomerId();
    }

    /**
     * 添加货运单
     *
     * @param wayBill
     * @return
     */
    @Override
    public WayBill add(WayBill wayBill) {

        try {
            CustomerClear customerClear = new CustomerClear();
            customerClear.setCustomerId(wayBill.getPayCusomer());
            customerClear.setFreight(wayBill.getFreight());
            customerClear.setInsurance(wayBill.getInsurance());
            customerClear.setIsClear(false);
            customerClear.setWayBillId(wayBill.getId());
            customerClear.setWriteDate(wayBill.getWriteDate());
            customerClearDao.save(customerClear);
            return wayBillDao.save(wayBill);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
}
