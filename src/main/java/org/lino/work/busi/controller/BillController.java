package org.lino.work.busi.controller;

import io.swagger.annotations.Api;
import org.lino.work.base.bean.Customer;
import org.lino.work.base.bean.Employee;
import org.lino.work.base.bean.Page;
import org.lino.work.base.bean.WayBill;
import org.lino.work.base.util.Result;
import org.lino.work.service.ICustomerService;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/bill")
@ControllerAdvice
@RestController
@Api(value = "货运单据 controller")
public class BillController {

    @Autowired
    private IWayBillService wayBillService;

    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value = "/findWayBillByBillId/{billId}",method = RequestMethod.GET)
    public WayBill findWayBillByBillId(@PathVariable("billId")String billId){

        WayBill bill = wayBillService.findWayBillByBillId(billId);

        return bill;
    }

    @RequestMapping(value = "/updateWayBillByBillId/{billId}",method = RequestMethod.PUT)
    public String updateWayBillByBillId(@PathVariable("billId")String billId, WayBill wayBill){

        boolean b = wayBillService.updateWayBillByBillId(billId,wayBill);
        if (b == true){
            return ReturnType.SUCCESS;
        }
        return ReturnType.ERROR;
    }

    @RequestMapping(value = "/deleteWayBillByBillId/{billId}",method = RequestMethod.DELETE)
    public String deleteWayBillByBillId(@PathVariable("billId")String billId){

        boolean b = wayBillService.deleteWayBillByBillId(billId);
        if (b == true){
            return ReturnType.SUCCESS;
        }
        return ReturnType.ERROR;
    }

    @RequestMapping(value = "/findWayBillbyState/{state}",method = RequestMethod.GET)
    public Result findWayBillbyState(@PathVariable("state")String state,@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

//        List<WayBill> bill = wayBillService.findWayBillbyState(state);
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        org.springframework.data.domain.Page<WayBill> page = wayBillService.findWayBillbyState(state,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findAllWayBill",method = RequestMethod.GET)
    public Result findAllWayBill(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

//        List<WayBill> bill = wayBillService.findAllWayBill();
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        org.springframework.data.domain.Page<WayBill> page = wayBillService.findAllWayBill(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findAllCustomerId",method = RequestMethod.GET)
    public List<String> findAllCustomerId(){

        List<String> customerId = customerService.findAllCustomerId();

        return customerId;
    }

    @RequestMapping(value = "/findCustomerByCustomerId/{customerId}",method = RequestMethod.GET)
    public Customer findCustomerByCustomerId(@PathVariable("customerId")String customerId){

        Customer customer = customerService.findCustomerByCustomerId(customerId);

        return customer;
    }

    @RequestMapping(value = "/addWayBill",method = RequestMethod.POST,produces = "application/json")
    public String addWayBill(WayBill waybill){

        boolean flag = wayBillService.addWayBIll(waybill);

        if (flag == true){
            return ReturnType.SUCCESS;
        }
        return ReturnType.ERROR;
    }


}
