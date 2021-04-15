/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.dtos;

import java.sql.Date;

/**
 *
 * @author jack3
 */
public class UserDTO {

    private String email, pass, name, code, phone, address;
    private Date createDate;
    private boolean status;

    public UserDTO() {
    }

    public UserDTO(String email, String pass, String name, String code, String phone, String address, Date createDate, boolean status) {
        this.email = email;
        this.pass = pass;
        this.name = name;
        this.code = code;
        this.phone = phone;
        this.address = address;
        this.createDate = createDate;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
