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
import js.daos.OrderDAO;
import js.dtos.ErrorDTO;
import js.dtos.OrderDetailDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class DeleteOrderHistoryController extends HttpServlet {
private final static String SUCCESS = "history.jsp";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(DeleteOrderHistoryController.class);
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
            String orderID = request.getParameter("orderID");
            ErrorDTO error = new ErrorDTO();

            OrderDAO dao = new OrderDAO();
            List<OrderDetailDTO> list = dao.getListOrderDetail(orderID);
            Date smallest = list.get(0).getStartDate();
            for (int i = 0; i < list.size(); i++) {
                if (smallest.compareTo(list.get(i).getStartDate()) < 0) {
                    smallest = list.get(i).getStartDate();
                }
            }
            long millis = System.currentTimeMillis();
            Date today = new Date(millis);
            if (today.compareTo(smallest) < 0) {
                dao.deleteItem(orderID, false);
                error.setLine1("delete success");
            } else {
                error.setLine1("you cant delete order from past or being process");
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
