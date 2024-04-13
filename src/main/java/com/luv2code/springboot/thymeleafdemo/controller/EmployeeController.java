package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //ad mapping for listing employees
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        //get employees from db
        List<Employee> theEmployees = employeeService.findAll();
        //add to spring model
        theModel.addAttribute("employees", theEmployees);
        //return Thymeleaf template
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
        public String showFormForAdd(Model theModel){
            //create model attribute to bind form data
            Employee employee = new Employee();

            theModel.addAttribute("employee", employee);

            return "employees/employee-form";
        }

    @PostMapping("/employees/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        //save employee
        employeeService.save(employee);
        //use redirection to prevent duplicate submissions with Post/redirect/get pattern
        return "redirect:/employees/list";
    }
}