/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.daos;

import js.controllers.Controller;
import js.dtos.CarDTO;
import js.dtos.CategoryDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import js.ultils.DBUtil;

/**
 *
 * @author jack3
 */
public class CarDAO {

    private final static Logger LOG = Logger.getLogger(CarDAO.class);
    Controller con = new Controller();

    public List<CategoryDTO> getListSub() throws ClassNotFoundException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CategoryDTO> list = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT categoryID, categoryName FROM tblCategory";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String name = rs.getString(2);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(new CategoryDTO(ID, name));
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

    public int getTotalByID(String id, Date start, Date end) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int total = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = " SELECT d.carID, SUM(d.quantity)AS[total] FROM\n"
                    + " tblOrder o JOIN tblOrderDetail d ON o.orderID = d.orderID WHERE o.status = 1 \n"
                    + " AND ((? <= startDate AND startDate <= ? AND ? <= endDate)OR\n"
                    + "(startDate <= ? AND ? <= endDate AND endDate <= ?)OR\n"
                    + "(? <= startDate AND endDate <= ?)OR\n"
                    + "(startDate <= ? AND ? <= endDate)) "
                    + "AND carID = ? GROUP BY d.carID";
            ps = conn.prepareStatement(sql);
            ps.setDate(1, start);
            ps.setDate(2, end);
            ps.setDate(3, end);
            ps.setDate(4, start);
            ps.setDate(5, start);
            ps.setDate(6, end);
            ps.setDate(7, start);
            ps.setDate(8, end);
            ps.setDate(9, start);
            ps.setDate(10, end);
            ps.setString(11, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(2);
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
        return total;
    }

        public int getTotal(String name, String cate, Date start, Date end, int quant) throws ClassNotFoundException, ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalItem = 1;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT COUNT(carID) AS totalItem FROM tblCar \n"
                    + "WHERE status = 1 AND carName LIKE ? AND categoryID LIKE ? AND quantity >= ?\n"
                    + "AND( carID NOT IN (SELECT c.carID FROM( SELECT d.carID, SUM(d.quantity)AS[total] FROM \n"
                    + "tblOrder o JOIN tblOrderDetail d ON o.orderID = d.orderID WHERE o.status = 1 AND (\n"
                    + "(? <= startDate AND startDate <= ? AND ? <= endDate)OR\n"
                    + "(startDate <= ? AND ? <= endDate AND endDate <= ?)OR\n"
                    + "(? <= startDate AND endDate <= ?)OR\n"
                    + "(startDate <= ? AND ? <= endDate))\n"
                    + "GROUP BY d.carID) a JOIN tblCar c ON a.carID = c.carID WHERE quantity - total < ?)"
                    + ")";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + cate + "%");
            ps.setInt(3, quant);
            ps.setDate(4, start);
            ps.setDate(5, end);
            ps.setDate(6, end);
            ps.setDate(7, start);
            ps.setDate(8, start);
            ps.setDate(9, end);
            ps.setDate(10, start);
            ps.setDate(11, end);
            ps.setDate(12, start);
            ps.setDate(13, end);
            ps.setInt(14, quant);
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

    public List<CarDTO> getListItem(String name, String cate, Date start, Date end, int pageNum, int quant) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarDTO> list = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT d.carID, d.categoryID, d.carName, d.description, d.year, d.color, d.price, d.quantity, d.imgLink FROM (\n"
                    + "SELECT ROW_NUMBER() OVER (ORDER BY year DESC) AS RowNum, carID FROM tblCar \n"
                    + "WHERE status = 1 AND carName LIKE ? AND categoryID LIKE ? AND quantity >= ?\n"
                    + "AND( carID NOT IN (SELECT c.carID FROM( SELECT d.carID, SUM(d.quantity)AS[total] FROM \n"
                    + "tblOrder o JOIN tblOrderDetail d ON o.orderID = d.orderID WHERE o.status = 1 AND (\n"
                    + "(? <= startDate AND startDate <= ? AND ? <= endDate)OR\n"
                    + "(startDate <= ? AND ? <= endDate AND endDate <= ?)OR\n"
                    + "(? <= startDate AND endDate <= ?)OR\n"
                    + "(startDate <= ? AND ? <= endDate))\n"
                    + "GROUP BY d.carID) a JOIN tblCar c ON a.carID = c.carID WHERE quantity - total < ?)\n"
                    + ")) b JOIN tblCar d ON b.carID = d.carID WHERE RowNum >= (1 + ?) AND RowNum <= (4 + ?) ORDER BY RowNum";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + cate + "%");
            ps.setInt(3, quant);
            ps.setDate(4, start);
            ps.setDate(5, end);
            ps.setDate(6, end);
            ps.setDate(7, start);
            ps.setDate(8, start);
            ps.setDate(9, end);
            ps.setDate(10, start);
            ps.setDate(11, end);
            ps.setDate(12, start);
            ps.setDate(13, end);
            ps.setInt(14, quant);
            ps.setInt(15, 4 * (pageNum - 1));
            ps.setInt(16, 4 * (pageNum - 1));
            rs = ps.executeQuery();
            while (rs.next()) {
                String ID = rs.getString(1);
                String cateID = rs.getString(2);
                String carName = rs.getString(3);
                String des = rs.getString(4);
                int year = rs.getInt(5);
                String color = rs.getString(6);
                float price = rs.getFloat(7);
                int quantity = rs.getInt(8);
                String link = rs.getString(9);
                int qu = getTotalByID(ID, start, end);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(new CarDTO(ID, carName, des, cateID, color, quantity - qu, year, price, true,link));

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

    public CarDTO getCar(String carID, Date start, Date end) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CarDTO car = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT carID, categoryID, carName, description, year, color, price, quantity,imgLink FROM  tblCar WHERE carID = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, carID);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ID = rs.getString(1);
                String cateID = rs.getString(2);
                String carName = rs.getString(3);
                String des = rs.getString(4);
                int year = rs.getInt(5);
                String color = rs.getString(6);
                float price = rs.getFloat(7);
                int quantity = rs.getInt(8);
                String link = rs.getString(9);
                int qu = 0;
                if (start != null && end != null) {
                    qu = getTotalByID(ID, start, end);
                }
                car = new CarDTO(ID, carName, des, cateID, color, quantity - qu, year, price, true,link);
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
        return car;
    }
}
