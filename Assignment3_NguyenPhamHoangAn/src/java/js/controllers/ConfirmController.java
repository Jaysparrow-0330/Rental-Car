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
import js.daos.CarDAO;
import js.daos.OrderDAO;
import js.dtos.CarDTO;
import js.dtos.ErrorDTO;
import js.dtos.OrderDTO;
import js.dtos.OrderDetailDTO;
import js.dtos.UserDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class ConfirmController extends HttpServlet {
   private final static String SUCCESS = "ToCartController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(ConfirmController.class);


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
            UserDTO user = (UserDTO) ss.getAttribute("LOGIN_USER");
            OrderDTO cart = (OrderDTO) ss.getAttribute("CART");
            Controller con = new Controller();
            ErrorDTO error = new ErrorDTO();
            CarDAO dao = new CarDAO();
            OrderDAO orDao = new OrderDAO();
            if (cart != null) {
                Map<String, OrderDetailDTO> list = cart.getList();
                if (list != null) {
                    if (!list.isEmpty()) {
                        String err = "";
                        for (Map.Entry<String, OrderDetailDTO> entry : list.entrySet()) {
                            OrderDetailDTO value = entry.getValue();
                            CarDTO cardto = dao.getCar(value.getCarID(), value.getStartDate(), value.getEndDate());
                            if (!con.checkQuantity(list, cardto.getQuantity(), cardto.getCarID(), value.getStartDate(), value.getEndDate(), 0)) {
                                err += "[" + cardto.getCarID() + "] ";
                            }
                        }
                        if ("".equals(err)) {
                            if (user != null) {
                                String email = user.getEmail();
                                cart.setEmail(email);
                                int ID = orDao.createOrder(cart);
                                orDao.createOrderDetail(list, ID);
                                ss.removeAttribute("CART");
                                error.setLine1("Order success!!!");
                            }
                        } else {
                            error.setLine1("Out of quantity: " + err);
                        }
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
