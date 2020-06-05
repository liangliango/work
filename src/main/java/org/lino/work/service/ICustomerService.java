package org.lino.work.service;

import org.lino.work.base.bean.Customer;
import org.lino.work.base.bean.WayBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    /**
     * 新增一个客户并关联用户
     * @param customer
     * @return
     */
    public boolean save(Customer customer);

    /**
     * 删除一个客户
     * @param customerId
     * @return
     */
    public boolean delete(String customerId);

    /**
     * 通过客户id删除客户
     * @param customerCode
     * @param customer
     * @return
     */
    public boolean update(String customerCode, Customer customer);

    /**
     * 分页查询所有客户
     * @param pageable
     * @return
     */
    public Page<Customer> selectAllCusByPage(Pageable pageable);

    /**
     * 查询一个客户
     * @param customerCode
     * @return
     */
    public Customer selectByCustomerCode(String customerCode);

    /**
     * 查询所有客户
     * @return
     */
    public List<String> selectAllCusCode();

    /**
     * 添加货运单
     * @param wayBill
     * @return
     */
    public WayBill add(WayBill wayBill);

    List<String> findAllCustomerId();

    Customer findCustomerByCustomerId(String customerId);

    Page<Customer> findAllCusomer(Pageable pageable);

    boolean addCustomer(Customer customer);

    String updateCustomerByCustomerId(String customerId, Customer customer);
}
