package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.models.Employee;
import com.app.repository.EmployeeRepository;

@Controller
public class EmployeeController {
  
  @Autowired
  EmployeeRepository employeeRepository;

  @GetMapping("/")
  public String showHome(Model model) {
    List<Employee> employees = employeeRepository.findAll();
    model.addAttribute("employeeObject", employees);
    return "home";
  }

  @GetMapping("/add-employee")
  public String addEmployee(Model model) {
    model.addAttribute("employeeObject", new Employee());
    return "add_employee";
  }

  @PostMapping("/add-employee")
  public String viewEmployees(@ModelAttribute Employee employee) {
    System.out.println(employee);
    employeeRepository.save(employee);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String deleteEmployee(@PathVariable int id, RedirectAttributes redirectAttributes) {
      Employee employee = employeeRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID: " + id));
      
      employeeRepository.delete(employee);
      
      redirectAttributes.addFlashAttribute("message", "Employee deleted successfully!");
      return "redirect:/";
  }

  @GetMapping("/update/{id}")
  public String updateEmployee(@PathVariable int id, Model model) {
    Employee employee = employeeRepository.findById(id).orElseThrow();
    model.addAttribute("employeeObject", employee);
    return "update_employee";
  }

  @PostMapping("/update/{id}")
  public String updateEmployee(@PathVariable int id, @ModelAttribute Employee employee) {
    employee.setId(id);
    employeeRepository.save(employee);
    return "redirect:/";
  }

  @GetMapping("/employees/sort-by-salary")
  public String sortBySalary(@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder, Model model) {
      List<Employee> employees;
      
      if ("asc".equals(sortOrder)) {
          employees = employeeRepository.findAllByOrderBySalaryAsc();
      } else {
          employees = employeeRepository.findAllByOrderBySalaryDesc();
      }
      
      model.addAttribute("employeeObject", employees);
      return "home";
  }

  // Filtering by Department
  @GetMapping("/employees/filter-by-department")
  public String filterByDepartment(@RequestParam("department") String department, Model model) {
    List<Employee> employees = employeeRepository.findByDepartment(department);
    model.addAttribute("employeeObject", employees);
    return "home";
  }
}
