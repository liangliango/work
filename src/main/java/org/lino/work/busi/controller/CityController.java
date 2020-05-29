package org.lino.work.busi.controller;

import io.swagger.annotations.Api;
import org.lino.work.base.bean.BillRoute;
import org.lino.work.base.bean.City;
import org.lino.work.base.bean.CityRoute;
import org.lino.work.base.bean.Driver;
import org.lino.work.service.IBillRouteService;
import org.lino.work.service.ICityLinkService;
import org.lino.work.service.ICityRouteService;
import org.lino.work.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/findAllCity",method = RequestMethod.GET)
    public List<City> findAllCity(){
        return cityService.findAllCity();
    }

    @RequestMapping(value = "/findRouteByStartAndEnd",method = RequestMethod.GET)
    public List<CityRoute> findRouteByStartAndEnd(@PathVariable("start") String start,@PathVariable("end")String end){
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
    public String addCity(City city){
        boolean b = cityService.addCity(city);
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping(value = "/addCityRoute",method = RequestMethod.POST)
    public String addCityRoute(CityRoute cityRoute){
        boolean b = cityRouteService.addCityRoute(cityRoute);
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }
    @RequestMapping(value = "/addCityLink",method = RequestMethod.POST)
    public String addCityLink(@PathVariable("cityId") int cityId,@PathVariable("cityLink") int cityLink){
        boolean b = cityLinkService.addCityLink(cityId,cityLink);
        if (b==true){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping(value = "/findCityRouteByStartAndEnd",method = RequestMethod.GET)
    public List<CityRoute> findCityRouteByStartAndEnd(String startStation,String endStation){
        return cityRouteService.findCityRouteByStartAndEnd(startStation,endStation);
    }

    @RequestMapping(value = "/findAllCityRoute",method = RequestMethod.GET)
    public List<CityRoute> findAllCityRoute(){
        return cityRouteService.findAllCityRoute();
    }
}
