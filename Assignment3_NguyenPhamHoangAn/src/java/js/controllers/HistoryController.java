/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import js.daos.OrderDAO;
import js.dtos.ErrorDTO;
import js.dtos.OrderDTO;
import js.dtos.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class HistoryController extends HttpServlet {
 private final static String SUCCESS = "history.jsp";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(HistoryController.class);
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession ss = request.getSession();
            String date = request.getParameter("date");
            String name = request.getParameter("name");
            String pageNum = request.getParameter("pageNum");
            String btn = request.getParameter("btn");
            Controller con = new Controller();
            ErrorDTO error = new ErrorDTO();
            Date update = null;
            boolean check = true;

            int page = 0;
            if (pageNum == null || "".equals(pageNum)) {
                page = 1;
            } else {
                page = Integer.parseInt(pageNum);
            }
            if (name == null) {
                name = "";
            }
            if ("date".equals(btn)) {
                name = "";
            } else {
                date = "";
            }
            if (date != null && !"".equals(date)) {
                if (!con.checkDate(date, 0)) {
                    check = false;
                    error.setLine1("please select date in past");
                } else {
                    update = Date.valueOf(date);
                }
            }
            if (check) {
                UserDTO user = (UserDTO) ss.getAttribute("LOGIN_USER");
                if (user != null) {
                    String email = user.getEmail();
                    OrderDAO dao = new OrderDAO();
                    int total = dao.getTotal(email, update, name);
                    int totalPage = con.getTotalPage(total);
                    if (page > totalPage) {
                        page = totalPage;
                    }
                    List<OrderDTO> list = dao.getListOrder(email, page, update, name);
                    if (list != null) {
                        request.setAttribute("LIST", list);
                        request.setAttribute("TOTAL_PAGE", totalPage);
                        request.setAttribute("PAGE_NUM", page);
                    } else {
                        error.setLine3("No order match to your request!");
                    }
                }
            }
            request.setAttribute("ERROR", error);
            url = SUCCESS;

        } catch (Exception e) {
            LOG.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
