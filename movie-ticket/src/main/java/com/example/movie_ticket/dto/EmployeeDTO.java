package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;

public class EmployeeDTO implements Validator {

    @NotEmpty
    @Size(min = 5,message = "Không được ít hơn 5 ký tự")
    @Size(max = 45,message = "Không được nhiều hơn 45 ký tự")
    private String fullName;

    @Email(message = "Không đúng định dạng email")
    private String email;

    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})",message = "Không đúng định dạng số điện thoại")
    private String phoneNumber;

    @NotEmpty
    private String birthday;
    private String idCard;

    @NotEmpty
    private String address;
    private Account account;

    public EmployeeDTO() {
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDTO formDTO = (EmployeeDTO) target;
        if (formDTO.idCard.toString().length() != 9 && formDTO.idCard.toString().length() != 12) {
            errors.rejectValue("idCard",null,"ID card chỉ được nhập từ 9 hoặc 12 số");
        }
        LocalDate now = LocalDate.now();
        Period period = Period.between(LocalDate.parse(formDTO.birthday),now);
        int year = period.getYears();
        System.out.printf(String.valueOf(year));
        if (year <18){
            errors.rejectValue("birthday",null,"phải trên 18 tuổi");
        }
    }
}
