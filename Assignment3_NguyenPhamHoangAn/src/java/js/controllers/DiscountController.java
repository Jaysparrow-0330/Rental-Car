/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import js.daos.OrderDAO;
import js.dtos.ErrorDTO;
import js.dtos.OrderDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class DiscountController extends HttpServlet {

    private final static String SUCCESS = "ToCartController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(DiscountController.class);
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
            String discountID = request.getParameter("discount");
            OrderDAO dao = new OrderDAO();
            ErrorDTO error = new ErrorDTO();
            OrderDTO cart = (OrderDTO) ss.getAttribute("CART");
            if (!"".equals(discountID) && discountID != null) {
                float value = dao.getDiscount(discountID);
                if (value > 0) {
                    if (cart != null) {
                        if (cart.getList() != null) {
                            cart.setDiscountID(discountID);
                            float disprice = cart.getTotalPrice() - (Math.round((cart.getTotalPrice() * value / 100) * 100) / 100);
                            cart.setDiscountPrice(disprice);
                        }
                    }
                }
                else{
                    error.setLine1("this code not exist or out of date");
                }
            }
            request.setAttribute("ERROR", error);
            ss.setAttribute("CART", cart);
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
