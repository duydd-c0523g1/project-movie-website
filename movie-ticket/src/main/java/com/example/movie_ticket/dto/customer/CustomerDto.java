package com.example.movie_ticket.dto.customer;

import com.example.movie_ticket.model.Account;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import javax.validation.constraints.NotBlank;


public class CustomerDto implements Validator {

    private Long id;
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;
    @NotBlank(message = "Email khách hàng không được để trống")
    private String email;
    @NotBlank(message = "Số điện thoại của khách hàng không được để trống")
    private String phoneNumber;
    @NotBlank(message = "Ngày tháng năm sinh của khách hàng không được để trống")
    private String birthday;
    @NotBlank(message = "CCCD/CMND của khách hàng không được để trống")
    private String idCard;
    private String address;
    private Account account;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String fullName, String email, String phoneNumber, String birthday, String idCard, String address, Account account) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.idCard = idCard;
        this.address = address;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
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
        CustomerDto customerDto = (CustomerDto) target;
        if (customerDto.getFullName().length() < 5) {
            errors.rejectValue("fullName", null, "Tổng số ký tự của tên khách hàng phải lớn hơn hoặc bằng 5");
        }
        if (customerDto.getFullName().length() > 45) {
            errors.rejectValue("fullName", null, "Tổng số ký tự của tên khách hàng phải bé hơn hoặc bằng 45");
        }
        if (!customerDto.getEmail().matches("^.+@.+\\..+$")) {
            errors.rejectValue("email", null, "Gmail khách hàng nhập chưa đứng yêu cầu xin vui lòng nhập lại");
        }
        if (!customerDto.getPhoneNumber().matches("^0\\d{9}$")) {
            errors.rejectValue("phoneNumber", null, "Số điện thoại bạn nhập chưa đúng xin lòng nhập lại");
        }
        if (customerDto.getBirthday().matches("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d$")) {
            errors.rejectValue("birthday", null, "Ngày tháng năm sinh bạn nhập chưa đúng yêu cầu xin vui lòng nhập lại");
        }
        if (!customerDto.getIdCard().matches("(^\\d{9}$)|(^\\d{12}$)")) {
            errors.rejectValue("idCard", null, "Số CCCD/CMND khách hàng đã bị sai xin vui lòng nhập lại");
        }
    }
}
