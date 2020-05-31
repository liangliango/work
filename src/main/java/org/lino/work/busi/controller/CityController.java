package org.lino.work.busi.controller;

import io.swagger.annotations.Api;
import org.lino.work.base.bean.*;
import org.lino.work.base.util.Result;
import org.lino.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/city")
@ControllerAdvice
@RestController
public class CityController {

    @Autowired
    ICityService cityService;

    @Autowired
    ICityRouteService cityRouteService;

    @Autowired
    IBillRouteService billRouteService;

    @Autowired
    ICityLinkService cityLinkService;

    @Autowired
    ICarriageService carriageService;

    @RequestMapping(value = "/findAllCity",method = RequestMethod.GET)
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/findRouteByStartAndEnd",method = RequestMethod.GET)
    public List<CityRoute> findRouteByStartAndEnd(String start,String end){
        System.out.println(start+"------>"+end);
        return cityRouteService.findRouteByStartAndEnd(start,end);
    }

    @RequestMapping(value = "/findRouteByRouteId/{routeId}",method = RequestMethod.GET)
    public CityRoute findRouteByRouteId(@PathVariable("routeId") String routeId){
        return cityRouteService.findRouteByRouteId(routeId);
    }


    @RequestMapping(value = "/addBillRoute",method = RequestMethod.POST)
    public String addBillRoute(BillRoute billRoute){
        return billRouteService.addBillRoute(billRoute);
    }

    @RequestMapping(value = "/addCity",method = RequestMethod.POST)
    public String addCity(String city){
        System.out.println(city);
        boolean b = cityService.addCity(city);
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping(value = "/addCityRoute/{fetchTime}",method = RequestMethod.POST)
    public String addCityRoute(CityRoute cityRoute,@PathVariable String fetchTime){
        cityRoute.setFetchTime(Double.parseDouble(fetchTime));
        boolean b = cityRouteService.addCityRoute(cityRoute);
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/addCityLink",method = RequestMethod.POST)
    public String addCityLink(String cityId,String linkCity){

        System.out.println(cityId+"    "+linkCity);
        boolean b = cityLinkService.addCityLink(Integer.parseInt(cityId),Integer.parseInt(linkCity));
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping(value = "/findCityRouteByStartAndEnd",method = RequestMethod.GET)
    public List<CityRoute> findCityRouteByStartAndEnd(String startStation,String endStation){
        System.out.println(startStation+"------>"+endStation);
        return cityRouteService.findCityRouteByStartAndEnd(startStation,endStation);
    }

    @RequestMapping(value = "/findAllCityRoute",method = RequestMethod.GET)
    public List<CityRoute> findAllCityRoute(){
        return cityRouteService.findAllCityRoute();
    }



    @RequestMapping(value = "/findAllCarriageByState",method = RequestMethod.GET)
    public List<Carriage> findAllCarriageByState1(){
        String state= "未到";
        System.out.println(state);
        List<Carriage> page = carriageService.findAllCarriageByState(state);
        return page;
    }
}
