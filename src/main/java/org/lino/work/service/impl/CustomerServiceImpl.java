package org.lino.work.service.impl;

import cn.hutool.crypto.SecureUtil;
import org.lino.work.base.bean.*;
import org.lino.work.iobus.dao.ICustomerClearDao;
import org.lino.work.iobus.dao.ICustomerDao;
import org.lino.work.iobus.dao.IUserDao;
import org.lino.work.iobus.dao.IWayBillDao;
import org.lino.work.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("ICustomerService")
@Transactional(propagation = Propagation.REQUIRED)
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

        String id = kh+System.currentTimeMillis();
        customer.setCustomerId(id);
        try {
            customerDao.save(customer);
            User user = new User();
            String pwd = SecureUtil.md5("123456");
            user.setPassword(pwd);
            user.setLoginId(customer.getCustomerId());
            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(String customerId) {
        try {
            customerDao.deleteByCustomerId(customerId);
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
            customerClear.setWayBillId(wayBill.getBillId());
            customerClear.setWriteDate(wayBill.getWriteDate());
            customerClearDao.save(customerClear);
            return wayBillDao.save(wayBill);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> findAllCustomerId() {
        return customerDao.findAllCustomerId();
    }

    @Override
    public Customer findCustomerByCustomerId(String customerId) {
        return customerDao.findByCustomerId(customerId);
    }

    @Override
    public Page<Customer> findAllCusomer(Pageable pageable) {
        return customerDao.findAll(pageable);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return this.save(customer);
    }

    @Override
    public String updateCustomerByCustomerId(String customerId, Customer customer) {

        try {
            customer.setCustomerId(customerId);
            customerDao.save(customer);

            return "SUCCESS";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
