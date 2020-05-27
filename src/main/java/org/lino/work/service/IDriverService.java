package org.lino.work.service;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.WayBill;
import org.lino.work.base.bean.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDriverService {

    /**
     * 分页查询所有司机信息
     * @param pageable
     * @return
     */
    public Page<Driver> findAllByPage(Pageable pageable);

    /**
     * 添加一个司机并关联用户
     * @param driverInfo
     * @return
     */
    public boolean addNewDriver(Driver driverInfo);

    /**
     * 通过司机id更改司机信息
     *
     * @param driverInfo
     * @return
     */
    public boolean updateById(Driver driverInfo);

    /**
     * 通过司机id查询司机
     * @param id
     * @return
     */
    public Driver findById(String id);

    /**
     * 通过id删除司机
     * @param id
     * @return
     */
    public boolean deleteById(String id);

    /**
     * 查询所有司机id
     * @return
     */
    public List<String> findAllId();

    /**
     * 登记货物路线信息
     * @param billRoute
     * @return
     */
    public BillRoute sign(BillRoute billRoute);

    public BillRoute findByBillRouteId(String id);

    public Carriage add(Carriage carriage);

}
