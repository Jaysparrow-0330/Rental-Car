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
public class OrderDetailDTO {

    private String carID,link;
    private Date startDate, endDate;
    private int quantity,quantityDay;
    private float price,priceDay;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String carID, Date startDate, Date endDate, int quantity, int quantityDay, float price, float priceDay,String link) {
        this.carID = carID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.quantityDay = quantityDay;
        this.price = price;
        this.priceDay = priceDay;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityDay() {
        return quantityDay;
    }

    public void setQuantityDay(int quantityDay) {
        this.quantityDay = quantityDay;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(float priceDay) {
        this.priceDay = priceDay;
    }

   
   
}
