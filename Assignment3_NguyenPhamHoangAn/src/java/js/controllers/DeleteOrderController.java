/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import js.daos.OrderDAO;
import js.dtos.OrderDTO;
import js.dtos.OrderDetailDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class DeleteOrderController extends HttpServlet {
private final static String SUCCESS = "ToCartController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(DeleteOrderController.class);
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
            Controller con = new Controller();
            String carID = request.getParameter("carID");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String key = start + end + carID;
            OrderDTO cart = (OrderDTO) ss.getAttribute("CART");
            OrderDAO or = new OrderDAO();
            if (cart != null) {
                Map<String, OrderDetailDTO> list = cart.getList();
                if (list != null) {
                    if (list.containsKey(key)) {
                        list.remove(key);
                        cart.setTotalPrice(con.getTotalPrice(list));
                        if (cart.getDiscountID() != null && !"".equals(cart.getDiscountID())) {
                            float disprice = cart.getTotalPrice() - (Math.round((cart.getTotalPrice() * or.getDiscount(cart.getDiscountID()) / 100) * 100) / 100);
                            cart.setDiscountPrice(disprice);
                        }
                        ss.setAttribute("CART", cart);
                    }
                }
                if (list.size() <= 0) {
                    ss.removeAttribute("CART");
                }
            }
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
