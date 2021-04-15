/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import js.dtos.OrderDetailDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class Controller {

    private final static Logger LOG = Logger.getLogger(Controller.class);

    public String getAlphaNumericString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public boolean checkEmail(String email) {
        return email.matches("[a-z][a-zA-Z0-9_.]{5,32}@[a-z0-9]{2,}(.[a-z0-9]{2,4}){1,2}");
    }

    public boolean checkID(String ID) {
        return ID.matches("[a-zA-Z0-9-/_.]{1,10}");
    }

    public boolean checkString(String in) {
        return in.matches("[a-zA-Z0-9@$!%*?&#^-_. +]+");
    }

    public boolean checkDate(String date, int check) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            java.util.Date redate = formatter.parse(date);
            java.util.Date thisdate = new java.util.Date();
            if (check == 1) {
                return redate.compareTo(thisdate) >= 0;
            } else {
                return redate.compareTo(thisdate) <= 0;
            }
        } catch (ParseException ex) {
            return false;
        }
    }

    public boolean checkInt(String num) {
        try {
            int checkNum;
            checkNum = Integer.parseInt(num);
            if (checkNum <= 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getTRUEDate(String date) {
        String dates = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date redate = df.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(redate);
            cal.add(Calendar.DATE, 2);
            dates = df.format(cal.getTime());
        } catch (ParseException ex) {
            LOG.error(ex);
        }
        return dates;
    }

    public int getTotalPage(int total) {
        int totalPage;
        if (total <= 4) {
            totalPage = 1;
        } else if (total > 4 && total % 4 == 0) {
            totalPage = total / 4;
        } else {
            totalPage = (total / 4) + 1;
        }
        return totalPage;
    }

       public float getTotalPrice(Map<String, OrderDetailDTO> list) {
        float total = 0;
        for (Map.Entry<String, OrderDetailDTO> entry : list.entrySet()) {
            OrderDetailDTO value = entry.getValue();
            total += value.getPriceDay();
        }
        return total;
    }

    public boolean checkQuantity(Map<String, OrderDetailDTO> list, int quantity, String carID, Date startDate, Date endDate, int quant2) {
        boolean check = true;
        int total = 0;
        for (Map.Entry<String, OrderDetailDTO> entry : list.entrySet()) {
            OrderDetailDTO value = entry.getValue();
            if (carID.equals(value.getCarID())) {
                if ((value.getStartDate().compareTo(startDate) >= 0 && value.getEndDate().compareTo(endDate) <= 0)
                        || (value.getStartDate().compareTo(startDate) <= 0 && value.getEndDate().compareTo(endDate) >= 0)
                        || (value.getStartDate().compareTo(startDate) >= 0 && value.getStartDate().compareTo(endDate) <= 0 && value.getEndDate().compareTo(endDate) >= 0)
                        || (value.getStartDate().compareTo(startDate) <= 0 && value.getEndDate().compareTo(startDate) >= 0 && value.getEndDate().compareTo(endDate) <= 0)) {
                    total += value.getQuantity();
                }
            }
        }
        if ((quantity - total) < quant2) {
            check = false;
        }
        return check;
    }

}
