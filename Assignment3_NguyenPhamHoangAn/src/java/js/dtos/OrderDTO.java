/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.dtos;

import java.sql.Date;
import java.util.Map;

/**
 *
 * @author jack3
 */
public class OrderDTO {

    private String orderID, email, discountID;
    private float totalPrice,discountPrice;
    private Date orderDate;
    private Map<String, OrderDetailDTO> list;
    private boolean status;

    public OrderDTO(String orderID, String email, String discountID, float totalPrice, float discountPrice, Date orderDate, Map<String, OrderDetailDTO> list, boolean status) {
        this.orderID = orderID;
        this.email = email;
        this.discountID = discountID;
        this.totalPrice = totalPrice;
        this.discountPrice = discountPrice;
        this.orderDate = orderDate;
        this.list = list;
        this.status = status;
    }

    public OrderDTO() {
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }


    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Map<String, OrderDetailDTO> getList() {
        return list;
    }

    public void setList(Map<String, OrderDetailDTO> list) {
        this.list = list;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
