package org.lino.work.busi.controller;

import org.lino.work.base.bean.*;
import org.lino.work.base.util.Result;
import org.lino.work.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RequestMapping(value = "/manger")
@ControllerAdvice
@RestController
public class MangerController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    IDriverService driverService;

    @Autowired
    IEmployeeService employeeService;

    @Autowired
    IUserService userService;

    @Autowired
    IGroupService groupService;

    @Autowired
    IPageService pageService;

    @Autowired
    IPageWithGroupService pageWithGroupService;

    @RequestMapping(value = "/findAllCusomer",method = RequestMethod.GET)
    public Result findAllCusomer(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Customer> page = customerService.findAllCusomer(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/deleteCusomerByCustomerId/{customerId}",method = RequestMethod.DELETE)
        public String deleteCusomerByCustomerId(@PathVariable("customerId") String customerId){

            boolean b = customerService.delete(customerId);
            if (b){
                return "SUCCESS";
            }else {
                return "ERROR";
            }


    }

    @RequestMapping(value = "/addCusomer",method = RequestMethod.POST)
    public String addCusomer(Customer customer){
        boolean b = customerService.addCustomer(customer);
        if (b){
            return "SUCCESS";
        }

        return "ERROR";
    }

    @RequestMapping(value = "/findCustomerByCustomerId/{customerId}",method = RequestMethod.GET)
    public Customer findCustomerByCustomerId(@PathVariable("customerId") String customerId){

        return customerService.findCustomerByCustomerId(customerId);

    }

    @RequestMapping(value = "/updateCustomerByCustomerId/{customerId}",method = RequestMethod.PUT)
    public String updateCustomerByCustomerId(@PathVariable("customerId") String customerId,Customer customer){

        return customerService.updateCustomerByCustomerId(customerId,customer);

    }

    @RequestMapping(value = "/addDriver",method = RequestMethod.POST)
    public String addDriver(Driver driver){
        boolean b = driverService.addDriver(driver);
        if (b){
            return "SUCCESS";
        }

        return "ERROR";
    }

    @RequestMapping(value = "/findAllDriverByPage",method = RequestMethod.GET)
    public Result findAllDriverByPage(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Driver> page = driverService.findAllDriver(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/deleteDriverByDriverId/{driverId}",method = RequestMethod.DELETE)
    public String deleteDriverByDriverId(@PathVariable("driverId") String driverId){

        boolean b = driverService.deleteDriverByDriverId(driverId);
        if (b){
            return "SUCCESS";
        }else {
            return "ERROR";
        }
    }

    @RequestMapping("/findDriverByDriverId/{driverId}")
    public Driver findDriverByDriverId(@PathVariable("driverId") String driverId){

        return driverService.findDriverByDriverId(driverId);

    }

    @RequestMapping(value = "/updateDriverByDriverId/{driverId}",method = RequestMethod.PUT)
    public String updateDriverByDriverId(@PathVariable("driverId") String driverId,Driver driver){

        return driverService.updateDriverByDriverId(driverId,driver);

    }

    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public String addEmployee(Employee employee){
        boolean b = employeeService.addEmployee(employee);
        if (b){
            return "SUCCESS";
        }

        return "ERROR";
    }

    @RequestMapping(value = "/findAllEmployeeByPage",method = RequestMethod.GET)
    public Result findAllEmployeeByPage(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Employee> page = employeeService.findAllEmployeeByPage(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/deleteEmployeeByEmployeeId/{employeeId}",method = RequestMethod.DELETE)
    public String deleteEmployeeByEmployeeId(@PathVariable("employeeId") String employeeId){

        boolean b = employeeService.deleteEmployeeByEmployeeId(employeeId);
        if (b){
            return "SUCCESS";
        }else {
            return "ERROR";
        }


    }

    @RequestMapping("/findEmployeeByEmployeeId/{employeeId}")
    public Employee findEmployeeByEmployeeId(@PathVariable("employeeId") String employeeId){

        return employeeService.findEmployeeByEmployeeId(employeeId);

    }

    @RequestMapping(value = "/updateEmployeeByEmployeeId/{employeeId}",method = RequestMethod.PUT)
    public String updateEmployeeByEmployeeId(@PathVariable("employeeId") String employeeId,Employee employee){

        return employeeService.updateEmployeeByEmployeeId(employeeId,employee);

    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.PUT)
    public String changePwd(String loginId1,String password){
        System.out.println(loginId1+"----->"+password);
        return userService.changePwd(loginId1,password);

    }

    @RequestMapping(value = "/findAllGroupByPage",method = RequestMethod.GET)
    public Result findAllGroupByPage(@RequestParam("pageNum") int pageNum, @RequestParam("limit") int limit){

        Pageable pageable = PageRequest.of(pageNum - 1, limit);
        Page<Group> page = groupService.findAllGroupByPage(pageable);
        Result result = new Result(200, "SUCCESS", (int) page.getTotalElements(), page.getContent());
        return result;
    }

    @RequestMapping(value = "/deleteGroup/{id}",method = RequestMethod.DELETE)
    public String deleteGroupById(@PathVariable("id") int id){

        boolean b = groupService.deleteGroupById(id);
        if (b){
            return "SUCCESS";
        }else {
            return "ERROR";
        }


    }

    @RequestMapping(value = "/findAllGroup",method = RequestMethod.GET)
    public List<Group> findAllGroup(){
        return groupService.findAllGroup();
    }

    @RequestMapping(value = "/findAllPage",method = RequestMethod.GET)
    public List<org.lino.work.base.bean.Page> findAllPage(){
        return pageService.findAllPage();
    }

    @RequestMapping(value = "/findPageByGroupId/{groupId}",method = RequestMethod.GET)
    public List<PageWithGroup> findPageByGroupId(@PathVariable("groupId" )int groupId){
        return pageWithGroupService.findPageByGroupId(groupId);
    }

    @RequestMapping(value = "/addPageWithGroup/{groupId}",method = RequestMethod.POST)
    public String addPageWithGroup(@PathVariable int groupId, HttpServletRequest req){

        String[] arr = req.getParameterValues("array[]");
        int[] arr1 = new int[arr.length];
        int i =0;
        for (String s : arr
                ) {
            arr1[i++] = Integer.parseInt(s);
        }
        System.out.println(groupId);
        System.out.println(Arrays.toString(arr));
        return pageWithGroupService.addPageWithGroup(groupId,arr1);
    }
}
