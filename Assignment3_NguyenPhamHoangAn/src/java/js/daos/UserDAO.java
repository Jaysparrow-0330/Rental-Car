/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.daos;

import js.controllers.Controller;
import js.dtos.UserDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import js.ultils.DBUtil;

/**
 *
 * @author jack3
 */
public class UserDAO {

    private final static Logger LOG = Logger.getLogger(UserDAO.class);
    Controller con = new Controller();

    public String getCodeFromID(String email) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String code = "";
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT userCode FROM tblUser WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                code = rs.getString("userCode");
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
        return code;
    }

    public void updateUser(String email, boolean status) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE tblUser SET status = ? WHERE email = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setString(2, email);
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

    public String checkCode(String email, String code, boolean check) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name = "";
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT userName FROM tblUser WHERE email = ? AND userCode = ? AND status = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, code);
            ps.setBoolean(3, check);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("userName");
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
        return name;
    }

    public UserDTO checkLogin(String email, String userPass) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO dto = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT userName, userCode, phone, address, createDate, status FROM tblUser WHERE email = ? AND userPassword = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, userPass);
            rs = ps.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("userName");
                String userCode = rs.getString("userCode");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String createDate = rs.getString("createDate");
                boolean status = rs.getBoolean("status");
                Date date = null;
                if (createDate != null) {
                    date = Date.valueOf(con.getTRUEDate(createDate));
                }
                dto = new UserDTO(email, "", userName, userCode, phone, address, date, status);
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
        return dto;
    }

    public void newUser(UserDTO dto) throws ClassNotFoundException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO tblUser (email, userName, userPassword, phone, address, createDate, userCode, status) VALUES (?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getPass());
            ps.setString(4, dto.getPhone());
            ps.setString(5, dto.getAddress());
            ps.setDate(6, dto.getCreateDate());
            ps.setString(7, dto.getCode());
            ps.setBoolean(8, dto.isStatus());
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
}
