/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
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
public class AddToCartController extends HttpServlet {
private final static String SUCCESS = "SearchPageController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(AddToCartController.class);
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
            String carID = request.getParameter("carID");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            ErrorDTO error = new ErrorDTO();
            OrderDAO or = new OrderDAO();
            Date startDate = null;
            Date endDate = null;
            CarDAO dao = new CarDAO();
            Controller con = new Controller();
            if (start != null && end != null && !"".equals(start) && !"".equals(start)) {
                if (con.checkDate(start, 1)) {
                    startDate = Date.valueOf(start);
                }
                if (con.checkDate(end, 1)) {
                    endDate = Date.valueOf(end);
                }
            }
            CarDTO cardto = dao.getCar(carID, startDate, endDate);
            String key = start + end + carID;
            long days = endDate.getTime() - startDate.getTime();
            int quantityDay = (int) (days / 86400000) + 1;
            OrderDTO cart = (OrderDTO) ss.getAttribute("CART");
            if (cart == null) {
                cart = new OrderDTO();
                OrderDetailDTO car = new OrderDetailDTO(carID, startDate, endDate, 1, quantityDay, cardto.getPrice(), cardto.getPrice() * quantityDay,cardto.getLink());
                Map<String, OrderDetailDTO> list = new HashMap<>();
                list.put(key, car);
                cart.setList(list);
                cart.setTotalPrice(con.getTotalPrice(list));
                cart.setDiscountID(null);
                cart.setDiscountPrice(0);
                ss.setAttribute("CART", cart);
            } else {
                OrderDetailDTO car = new OrderDetailDTO(carID, startDate, endDate, 1, quantityDay, cardto.getPrice(), cardto.getPrice() * quantityDay,cardto.getLink());
                Map<String, OrderDetailDTO> list = cart.getList();
                boolean check = con.checkQuantity(list, cardto.getQuantity(), carID, startDate, endDate, 1);
                if (check) {
                    if (list.containsKey(key)) {
                        int quant = list.get(key).getQuantity();
                        car.setQuantity(quant + 1);
                    }
                    car.setPriceDay(car.getQuantity() * car.getQuantityDay() * car.getPrice());
                    list.put(key, car);
                    cart.setList(list);
                    cart.setTotalPrice(con.getTotalPrice(list));
                    if (cart.getDiscountID() != null && !"".equals(cart.getDiscountID())) {
                        float disprice = cart.getTotalPrice() - (Math.round((cart.getTotalPrice() * or.getDiscount(cart.getDiscountID()) / 100) * 100) / 100);
                        cart.setDiscountPrice(disprice);
                    }
                } else {
                    error.setLine4("out of quantity");
                }
            }
            ss.setAttribute("CART", cart);
            request.setAttribute("ERROR1", error);
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
