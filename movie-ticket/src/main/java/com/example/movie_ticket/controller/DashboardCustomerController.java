package com.example.movie_ticket.controller;

import com.example.movie_ticket.dto.customer.AccountCustomerDto;
import com.example.movie_ticket.dto.customer.CustomerDto;
import com.example.movie_ticket.model.Account;
import com.example.movie_ticket.model.Customer;
import com.example.movie_ticket.service.IAccountService;
import com.example.movie_ticket.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard/customer")
public class DashboardCustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    public String showdashboardCustomer(Model model,
                                        @RequestParam(defaultValue = "", required = false) String name,
                                        @RequestParam(defaultValue = "", required = false) String phone,
                                        @RequestParam(defaultValue = "0", required = false) int page) {
        Pageable pageable = PageRequest.of(page, 1);
        Page<Customer> customerPage = customerService.getAllCustomerPageable(pageable, name, phone);
        model.addAttribute("customerList", customerPage);
        model.addAttribute("name", name);
        model.addAttribute("phone", phone);
        return "/customer/dashboard-admin-customer";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/dashboard/customer";
    }

    @GetMapping("/view/{id}")
    public String getCustomerById(@PathVariable Long id,
                                  Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "/customer/view-customer";
    }

    @GetMapping("/edit/{id}")
    public String getCustomerByIdd(@PathVariable Long id,
                                   Model model) {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = customerService.getCustomerById(id);
        BeanUtils.copyProperties(customer, customerDto);
        model.addAttribute("customerDto", customerDto);
        return "/customer/edit-customer";
    }

    @PostMapping("/edit")
    public String editCustomer(@Validated @ModelAttribute CustomerDto customerDto,
                               BindingResult bindingResult,
                               Model model) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "/customer/edit-customer";
        }
        Long id = customerDto.getId();
        Customer customer1 = customerService.getCustomerById(id);
        List<Customer> customerList = customerService.getAllCustomer();
        if (!customer1.getEmail().equals(customerDto.getEmail())) {
            for (int i = 0; i < customerList.size(); i++) {
                if (customerDto.getEmail().equals(customerList.get(i).getEmail())) {
                    model.addAttribute("customerDto", customerDto);
                    model.addAttribute("messageEmail", "Email đã tồn tại xin vui lòng xem lại thông tin của bạn");
                    return "/customer/edit-customer";
                }
            }
        }
        if (!customer1.getPhoneNumber().equals(customerDto.getPhoneNumber())) {
            for (int i = 0; i < customerList.size(); i++) {
                if (customerDto.getPhoneNumber().equals(customerList.get(i).getPhoneNumber())) {
                    model.addAttribute("customerDto", customerDto);
                    model.addAttribute("messagePhoneNumber", " Số điện thoại đã tồn tại xin vui lòng xem lại thông tin của bạn");
                    return "/customer/create-customer";
                }
            }
        }
        if (!customer1.getIdCard().equals(customerDto.getIdCard())) {
            for (int i = 0; i < customerList.size(); i++) {
                if (customerDto.getIdCard().equals(customerList.get(i).getIdCard())) {
                    model.addAttribute("customerDto", customerDto);
                    model.addAttribute("messageIdCard", "Số CCCD/CMND đã tồn tại xin vui lòng xem lại thông tin của bạn");
                    return "/customer/create-customer";
                }
            }
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerService.updateCustomer(customer);
        return "redirect:/dashboard/customer";

    }
}
