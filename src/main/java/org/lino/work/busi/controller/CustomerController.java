package org.lino.work.busi.controller;

import org.lino.work.base.bean.WayBill;
import org.lino.work.base.util.Result;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@ControllerAdvice
public class CustomerController {

    @Autowired
    IWayBillService wayBillService;

    @RequestMapping(value = "/findWayBillbyStateAndSendId/{state}/{sendId}",method = RequestMethod.GET)
    public Result findWayBillbyStateAndSendId(@PathVariable("state") String state,@PathVariable("sendId") String sendId, @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<WayBill> page = wayBillService.findWayBillbyStateAndSendId(state,sendId,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());

        return result;
    }


    @RequestMapping(value = "/findAllWayBillBySendId/{sendId}",method = RequestMethod.GET)
    public Result findAllWayBillBySendId(@PathVariable("sendId") String sendId, @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<WayBill> page = wayBillService.findAllWayBillBySendId(sendId,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());

        return result;
    }

    @RequestMapping(value = "/findWayBillbyStateAndReciverId/{state}/{reciverId}",method = RequestMethod.GET)
    public Result findWayBillbyStateAndReciverId(@PathVariable("state") String state,@PathVariable("reciverId") String reciverId, @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<WayBill> page = wayBillService.findWayBillbyStateAndReciverId(state,reciverId,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());

        return result;
    }


    @RequestMapping(value = "/findAllWayBillByReciverId/{reciverId}",method = RequestMethod.GET)
    public Result findAllWayBillByReciverId(@PathVariable("reciverId") String reciverId, @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<WayBill> page = wayBillService.findAllWayBillByReciverId(reciverId,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());

        return result;
    }


    @RequestMapping(value = "/findAllCanClearByPayCustomer/{payCustomer}",method = RequestMethod.GET)
    public List<WayBill> findAllCanClearByPayCustomer(@PathVariable("payCustomer") String payCustomer){
        System.out.println(payCustomer);
        return wayBillService.findAllCanClearByPayCustomer(payCustomer);
    }



    @RequestMapping(value = "/findWayBillByBillId/{billId}",method = RequestMethod.GET)
    public WayBill findWayBillByBillId(@PathVariable("billId") String billId){

        return wayBillService.findWayBillByBillId(billId);
    }



    @RequestMapping(value = "/wayBillClearByBillId/{billId}",method = RequestMethod.PUT)
    public String wayBillClearByBillId(@PathVariable("billId") String billId){

        return wayBillService.wayBillClearByBillId(billId);
    }
}
