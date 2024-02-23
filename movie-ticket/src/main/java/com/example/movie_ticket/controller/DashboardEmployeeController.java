package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.EmployeeDTO;
import com.example.movie_ticket.model.Employee;
import com.example.movie_ticket.service.IAccountService;
import com.example.movie_ticket.service.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard/employee")
public class DashboardEmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public String showDashboardEmployee(@PageableDefault(value = 2) Pageable pageable,
                                        Model model) {
        model.addAttribute("employees",employeeService.findAllEmployee(pageable));
        return "/employee/dashboard-admin-employee";
    }

    @GetMapping("/add")
    public ModelAndView showFormAddEmployee(Model model) {
        model.addAttribute("accountList", accountService.findAllAccount());
        return new ModelAndView("/employee/create-employee","employee",new EmployeeDTO());
    }

    @PostMapping("/add")
    public ModelAndView saveEmployee(@Valid @ModelAttribute("employee") EmployeeDTO employeeDTO, BindingResult bindingResult) {
        employeeDTO.validate(employeeDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("employee/create-employee","employee", employeeDTO);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO,employee);
            employeeService.createEmployee(employee);
            return new ModelAndView("redirect:/dashboard/employee","employee",employee);
        }
    }


    @GetMapping("/view/{id}")
    public ModelAndView viewEmployee( @PathVariable Long id) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ModelAndView("/employee/error-404");
        }
        return new ModelAndView("/employee/view-employee","employee",employeeOptional.get());
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showFormEditEmployee( @PathVariable Long id, Model model) {
        Optional<Employee> employeeOptional = employeeService.findEmployeeById(id);
        if (!employeeOptional.isPresent()) {
            return new ModelAndView("/employee/error-404");
        }
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employeeOptional.get(),employeeDTO);
        model.addAttribute("accountList", accountService.findAllAccount());
        model.addAttribute("id", id);
        return new ModelAndView("/employee/edit-employee","employeeDTO",employeeDTO);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateEmployee(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                                       BindingResult bindingResult,
                                       Model model,
                                       @PathVariable Long id) {
        employeeDTO.validate(employeeDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            model.addAttribute("id", id);
            model.addAttribute("accountList", accountService.findAllAccount());
            return new ModelAndView("employee/edit-employee","employeeDTO", employeeDTO);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDTO,employee);
            employee.setId(id);
            employeeService.createEmployee(employee);
            return new ModelAndView("redirect:/dashboard/employee","employee",employee);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/dashboard/employee";
    }

    @GetMapping("/search")
    public ModelAndView searchEmployee(@PageableDefault(value = 2) Pageable pageable,
                                       @RequestParam String nameEmployee, Model model){
        Page<Employee> employees = employeeService.searchByName(nameEmployee,pageable);
        model.addAttribute("nameEmployee",nameEmployee);
        return new ModelAndView("/employee/dashboard-admin-employee","employees",employees);
    }
}
