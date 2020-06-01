package org.lino.work.busi.controller;

import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.Carriage;
import org.lino.work.base.bean.DriverClear;
import org.lino.work.base.util.Result;
import org.lino.work.service.IBillRouteService;
import org.lino.work.service.ICarriageService;
import org.lino.work.service.IDriverClearService;
import org.lino.work.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/driver")
@ControllerAdvice
@RestController
public class DriverController {

    @Autowired
    IDriverService driverService;

    @Autowired
    IDriverClearService driverClearService;

    @Autowired
    ICarriageService carriageService;

    @Autowired
    IBillRouteService billRouteService;


    @RequestMapping(value = "/findDriverClearByDriverIdAndState",method = RequestMethod.GET)
    public Result findDriverClearByDriverIdAndState(String driverId,String state,@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){


        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<DriverClear> page = driverClearService.findDriverClearByDriverIdAndState(driverId,state,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findDriverClearByCarriageId/{carriageId}",method = RequestMethod.GET)
    public DriverClear findDriverClearByCarriageId(@PathVariable String carriageId){
        return driverClearService.findDriverClearByCarriageId(carriageId);
    }

    @RequestMapping(value = "/findCarriageByDriverIdAndState/{driverId}/{state}",method = RequestMethod.GET)
    public Result findCarriageByDriverIdAndState(@PathVariable("driverId") String driverId, @PathVariable("state") String state,
                                                 @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){
        System.out.println(state+"---------"+driverId);
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Carriage> page = driverClearService.findCarriageByDriverIdAndState(driverId, state, pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findCarriageByDriverId/{driverId}",method = RequestMethod.GET)
    public Result findCarriageByDriverId(@PathVariable("driverId") String driverId,
                                                         @RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Carriage> page = carriageService.findCarriageByDriverId(driverId, pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findAllCarriage",method = RequestMethod.GET)
    public List<Carriage> findAllCarriage(){

        return carriageService.findAllCarriage2();
    }

    @RequestMapping(value = "/findBillRouteByBillId/{billId}",method = RequestMethod.GET)
    public BillRoute findBillRouteByBillId(@PathVariable String billId){
        return billRouteService.findBillRouteByBillId(billId);
    }

    @RequestMapping(value = "/savaNowbyBillId",method = RequestMethod.PUT)
    public String savaNowbyBillId(String billId,String now){
        return billRouteService.savaNowbyBillId(billId ,now);
    }

    @RequestMapping(value = "/arrive",method = RequestMethod.PUT)
    public String arrive(String carriageId){
        return carriageService.arrive(carriageId);
    }


    @RequestMapping(value = "/findCarriageByCarriageId/{carriageId}",method = RequestMethod.GET)
    public Carriage findCarriageByCarriageId(@PathVariable String carriageId){
        return carriageService.findCarriageByCarriageId(carriageId);
    }
}
