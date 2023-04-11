package com.example.demo.controller;

import com.example.demo.entity.Employee;
import org.springframework.ui.Model;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Async
    @GetMapping("/")
    public String getBusiness(Model model){
        model.addAttribute("list",employeeService.getAllList());
        return "business";
    }
    @Async
    @GetMapping("/switch/{id}")
    public String switchLight(@PathVariable("id") String id){
        employeeService.switchStatus(id);
        return "redirect:/";
    }

    @Async
    @GetMapping("/admin")
    public String getAdminPanel(Model model){
        model.addAttribute("list",employeeService.getAllList());
        return "adminPanel";
    }
    @Async
    @GetMapping("/admin/{id}")
    public String getDataForEdit(@PathVariable("id") String id,Model model){
        model.addAttribute("employee",employeeService.getById(id));
        return "editEmployee";
    }
    @Async
    @PostMapping("/admin/{id}")
    public String postDataForEdit(@PathVariable("id") String id,
                                  @RequestParam(value = "firstName",required = false) String firstName,
                                  @RequestParam(value = "lastName",required = false) String lastName,
                                  @RequestParam(value = "dateOfBirth",required = false) String dateOfBirth,
                                  @RequestParam(value = "phoneNumber",required = false) String phoneNumber,
                                  @RequestParam(value = "codeOfDepartment",required = false) String codeOfDepartment,
                                  @RequestParam(value = "wage",required = false) String wage,
                                  @RequestParam(value = "status", required = false) String status
                                  ){
        Employee employee = new Employee(Long.parseLong(id), firstName, lastName, dateOfBirth, phoneNumber,codeOfDepartment,Integer.parseInt(wage),Boolean.getBoolean(status));
        employeeService.update(employee);
        return "redirect:/admin";
    }
    @Async
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") String id){
        employeeService.delete(id);
        return "redirect:/admin";
    }
    @Async
    @GetMapping("/admin/new")
    public String newEmployee(){
        employeeService.saveNew();
        return "redirect:/admin";
    }
}
