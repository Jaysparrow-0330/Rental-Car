/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.daos;

import js.controllers.Controller;
import js.dtos.OrderDTO;
import js.dtos.OrderDetailDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import js.ultils.DBUtil;

/**
 *
 * @author jack3
 */
public class OrderDAO {

    private final static Logger LOG = Logger.getLogger(OrderDAO.class);
    Controller con = new Controller();

    public float getDiscount(String discountID) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        float value = 0;
        if (discountID != null && !"".equals(discountID)) {
            long millis = System.currentTimeMillis();
            Date today = new Date(millis);
            try {
                conn = DBUtil.getConnection();
                String sql = "SELECT percentValue FROM tblDiscount WHERE startDate <= ? "
                        + "AND ? <= endDate AND status = 1 AND discountID = ?";
                ps = conn.prepareStatement(sql);
                ps.setDate(1, today);
                ps.setDate(2, today);
                ps.setString(3, discountID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    value = rs.getInt(1);
                }
            } catch (SQLException ex) {
                LOG.error(ex);
            } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException ex) {
                        LOG.error(ex);
                    }
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException ex) {
                        LOG.error(ex);
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        LOG.error(ex);
                    }
                }
            }
        }
        return value;
    }

    public void deleteItem(String orderID, boolean status) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE tblOrder SET status = ? WHERE orderID = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, orderID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
    }
    
    public int createOrder(OrderDTO dto) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String dis = "NONE";
        if (dto.getDiscountID() != null && !"".equals(dto.getDiscountID())) {
            dis = dto.getDiscountID();
        }
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO tblOrder (email, totalPrice, orderDate, discountID, status) VALUES(?,?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getEmail());
            ps.setFloat(2, dto.getTotalPrice());
            ps.setDate(3, today);
            ps.setString(4, dis);
            ps.setBoolean(5, true);
            ps.execute();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return id;
    }

    public void createOrderDetail(Map<String, OrderDetailDTO> list, int orderID) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            for (Map.Entry<String, OrderDetailDTO> entry : list.entrySet()) {
                OrderDetailDTO value = entry.getValue();
                String sql = "INSERT INTO tblOrderDetail (orderID, carID, startDate, endDate, price, quantity,imgLink) VALUES(?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, orderID);
                ps.setString(2, value.getCarID());
                ps.setDate(3, value.getStartDate());
                ps.setDate(4, value.getEndDate());
                ps.setFloat(5, value.getPrice());
                ps.setInt(6, value.getQuantity());
                 ps.setString(7, value.getLink());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
    }

    public int getTotal(String email, Date update, String name) throws ClassNotFoundException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String up = "";
        if (update != null) {
            up = update.toString();
        }
        int totalItem = 1;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(a.orderID) FROM ( \n"
                    + "SELECT orderID from tblOrderDetail d JOIN tblCar c ON d.carID = c.carID WHERE c.carName LIKE ? GROUP BY orderID) \n"
                    + "a JOIN tblOrder b ON a.orderID = b.orderID\n"
                    + "WHERE orderDate LIKE ? AND email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + up + "%");
            ps.setString(3, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                totalItem = rs.getInt(1);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return totalItem;
    }

    public List<OrderDTO> getListOrder(String email, int pageNum, Date update, String name) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDTO> list = null;
        String up = "";
        if (update != null) {
            up = update.toString();
        }
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT o.orderID, totalPrice, orderDate, discountID, status FROM ( \n"
                    + "SELECT ROW_NUMBER() OVER ( ORDER BY a.orderID DESC ) AS RowNum, a.orderID FROM ( \n"
                    + "SELECT orderID from tblOrderDetail d JOIN tblCar c ON d.carID = c.carID WHERE c.carName LIKE ? GROUP BY orderID) \n"
                    + "a JOIN tblOrder b ON a.orderID = b.orderID)\n"
                    + "i JOIN tblOrder o ON i.orderID = o.orderID \n"
                    + "WHERE orderDate LIKE ? AND email = ? AND status = 1 AND RowNum >= (1 + ?) AND RowNum <= (5 + ?) ORDER BY RowNum";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + up + "%");
            ps.setString(3, email);
            ps.setInt(4, 5 * (pageNum - 1));
            ps.setInt(5, 5 * (pageNum - 1));
            rs = ps.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                float totalPrice = rs.getFloat(2);
                String orderDate = rs.getString(3);
                String discount = rs.getString(4);
                float disPrice = 0;
                if (!"NONE".equals(discount)) {
                    disPrice = totalPrice - (Math.round((totalPrice * getDiscount(discount) / 100) * 100) / 100);
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                Date date = Date.valueOf(con.getTRUEDate(orderDate));
                list.add(new OrderDTO(orderID, email, discount, totalPrice, disPrice, date, null, true));
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return list;
    }

        public OrderDTO getOrder(String id) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT orderID, totalPrice, orderDate, discountID FROM tblOrder WHERE orderID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String orderID = rs.getString(1);
                float totalPrice = rs.getFloat(2);
                String orderDate = rs.getString(3);
                String discount = rs.getString(4);
                float disPrice = 0;
                if (!"NONE".equals(discount)) {
                    disPrice = totalPrice - (Math.round((totalPrice * getDiscount(discount) / 100) * 100) / 100);
                }
                Date date = Date.valueOf(con.getTRUEDate(orderDate));
                order = new OrderDTO(orderID, "", discount, totalPrice, disPrice, date, null, true);
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return order;
    }
    
    public List<OrderDetailDTO> getListOrderDetail(String orderID) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetailDTO> list = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT carID, startDate, endDate, price, quantity, imgLink FROM tblOrderDetail WHERE orderID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                String carID = rs.getString(1);
                String start = rs.getString(2);
                String end = rs.getString(3);
                float price = rs.getFloat(4);
                int quantity = rs.getInt(5);
                String link = rs.getString(6);
                if (list == null) {
                    list = new ArrayList<>();
                }
                Date startDate = Date.valueOf(con.getTRUEDate(start));
                Date endDate = Date.valueOf(con.getTRUEDate(end));
                long days = endDate.getTime() - startDate.getTime();
                int quantityDay = (int) (days / 86400000) + 1;
                list.add(new OrderDetailDTO(carID, startDate, endDate, quantity, quantityDay, price, price * quantityDay * quantity,link));
            }
        } catch (SQLException ex) {
            LOG.error(ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return list;
    }

}
