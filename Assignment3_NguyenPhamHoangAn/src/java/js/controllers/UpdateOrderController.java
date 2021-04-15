/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class UpdateOrderController extends HttpServlet {

    private final static String SUCCESS = "ToCartController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(UpdateOrderController.class);
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
            boolean check = true;
            HttpSession ss = request.getSession();
            Controller con = new Controller();
            ErrorDTO error = new ErrorDTO();
            String carID = request.getParameter("carID");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String quant = request.getParameter("quant");
            Date startDate = null;
            Date endDate = null;
            CarDAO dao = new CarDAO();
            OrderDAO or = new OrderDAO();
            int qua = 0;
            if ("".equals(quant) || quant == null) {
                check = false;
                error.setLine1("enter quantity");
            } else if (!con.checkInt(quant)) {
                check = false;
                error.setLine1("quantity must be integer number");
            } else {
                qua = Integer.parseInt(quant);
            }
            if (!con.checkDate(start, 1)) {
                check = false;
                error.setLine1("please select date in future");
            } else {
                startDate = Date.valueOf(start);
            }
            if (!con.checkDate(end, 1)) {
                check = false;
                error.setLine1("please select date in future");
            } else {
                endDate = Date.valueOf(end);
            }
            CarDTO cardto = dao.getCar(carID, startDate, endDate);
            String key = start + end + carID;
            OrderDTO cart = (OrderDTO) ss.getAttribute("CART");
            if (cart != null) {
                Map<String, OrderDetailDTO> list = cart.getList();
                if (list != null) {
                    if (check) {
                        if (list.containsKey(key)) {
                            int tmp = list.get(key).getQuantity();
                            int q = qua - tmp;
                            boolean check2 = con.checkQuantity(list, cardto.getQuantity(), carID, startDate, endDate, q);
                            if (check2) {
                                list.get(key).setQuantity(qua);
                                list.get(key).setPriceDay(qua * list.get(key).getPrice() * list.get(key).getQuantityDay());
                                cart.setTotalPrice(con.getTotalPrice(list));
                                if (cart.getDiscountID() != null && !"".equals(cart.getDiscountID())) {
                                    float disprice = cart.getTotalPrice() - (Math.round((cart.getTotalPrice() * or.getDiscount(cart.getDiscountID()) / 100) * 100) / 100);
                                    cart.setDiscountPrice(disprice);
                                }
                            } else {
                                list.get(key).setQuantity(tmp);
                                error.setLine1("out of quantity");
                            }
                        }
                    }
                }
            }
            ss.setAttribute("CART", cart);
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
