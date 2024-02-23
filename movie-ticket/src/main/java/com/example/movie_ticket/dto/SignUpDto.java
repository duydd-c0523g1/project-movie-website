package com.example.movie_ticket.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpDto implements Validator {
    @Size(min = 8, max = 50, message = "Username phải từ 8 đến 50 kí tự")
    private String username;
    @Size(min = 8, max = 20, message = "Mật khẩu phải từ 8 đến 20 kí tự")
    private String password;
    private boolean isEnabled = true;
    private String fullName;
    @Email
    private String email;
    private String emailAgain;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})",message = "Không đúng định dạng số điện thoại")
    private String phoneNumber;

    public SignUpDto() {
    }

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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAgain() {
        return emailAgain;
    }

    public void setEmailAgain(String emailAgain) {
        this.emailAgain = emailAgain;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpDto signUpDto = (SignUpDto) target;
        if (!signUpDto.getEmail().equals(signUpDto.getEmailAgain())) {
            errors.rejectValue("emailAgain", null, "Email nhập lại không khớp");
        }
    }
}
