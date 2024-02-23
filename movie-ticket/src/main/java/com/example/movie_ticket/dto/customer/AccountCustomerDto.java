package com.example.movie_ticket.dto.customer;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;

public class AccountCustomerDto implements Validator {
    @NotBlank(message = "Tên đăng nhập của bạn không được để trống")
    private String username;
    @NotBlank(message = "Mật khẩu của bạn không được để trống")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
