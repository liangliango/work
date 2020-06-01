package org.lino.work.busi.controller;

import io.swagger.annotations.Api;
import org.lino.work.base.bean.*;
import org.lino.work.base.util.Result;
import org.lino.work.service.ICarriageService;
import org.lino.work.service.ICityService;
import org.lino.work.service.IDriverService;
import org.lino.work.service.IWayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping(value = "/carriage")
@ControllerAdvice
@RestController
@Api(value = "运输合同 controller")
public class CarriageComtroller {

    @Autowired
    ICityService cityService;

    @Autowired
    IWayBillService wayBillService;

    @Autowired
    IDriverService driverService;

    @Autowired
    ICarriageService carriageService;

    @RequestMapping(value = "/findWayBillByState1",method = RequestMethod.GET)
    public List<WayBill> findWayBillByState1(){

        List<WayBill> wayBill = wayBillService.findWayBillByState1();

        return wayBill;

    }

    @RequestMapping(value = "/findAllCity",method = RequestMethod.GET)
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/findAllDriver",method = RequestMethod.GET)
    public Result findAllDriver(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Driver> page = driverService.findAllDriver(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/findAllDriver1",method = RequestMethod.GET)
    public List<Driver> findAllDriver1(){
        List<Driver> drivers= driverService.findAllDriver1();
        return drivers;
    }

    @RequestMapping(value = "/findWayBillByBillId/{billId}",method = RequestMethod.GET)
    public WayBill findWayBillByBillId(@PathVariable("billId")String billId){
        return wayBillService.findWayBillByBillId(billId);
    }

    @RequestMapping(value = "/findDriverByDriverId/{driverId}",method = RequestMethod.GET)
    public Driver findDriverByDriverId(@PathVariable("driverId")String driverId){
        return driverService.findDriverByDriverId(driverId);
    }

    @RequestMapping(value = "/addCarriage",method = RequestMethod.POST)
    public String addCarriage(Carriage carriage){
        return carriageService.addCarriage(carriage);
    }

    @RequestMapping(value = "/findCarriageByCarriageId/{carriageId}",method = RequestMethod.GET)
    public Carriage findCarriageByCarriageId(@PathVariable("carriageId")String carriageId){
        return carriageService.findCarriageByCarriageId(carriageId);
    }

    @RequestMapping(value = "/updateWayBillByBillId/{billId}",method = RequestMethod.GET)
    public String updateWayBillByBillId(@PathVariable("billId")String billId,WayBill wayBill){
        return carriageService.updateWayBillByBillId(billId,wayBill);
    }

    @RequestMapping(value = "/updateWayBillByBillId1/{billId}",method = RequestMethod.PUT)
    public String updateWayBillByBillId1(@PathVariable("billId")String billId){
        return carriageService.updateWayBillByBillId1(billId);
    }
    @RequestMapping(value = "/deleteCarriageByCarriageId/{carriageId}",method = RequestMethod.DELETE)
    public String deleteCarriageByCarriageId(@PathVariable("carriageId")String carriageId){
        return carriageService.deleteCarriageByCarriageId(carriageId);
    }

    @RequestMapping(value = "/findAllCarriageByState/{state}",method = RequestMethod.GET)
    public Result findAllCarriageByState(@PathVariable("state")String state,@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){
        System.out.println(state);
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Carriage> page = carriageService.findAllCarriageByState(state,pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }


    @RequestMapping(value = "/findAllCarriage",method = RequestMethod.GET)
    public Result findAllCarriage(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){
        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Carriage> page = carriageService.findAllCarriage(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/updateCarriage",method = RequestMethod.PUT)
    public String updateCarriage(Carriage carriage){
        return carriageService.updateCarriage(carriage);
    }




}
