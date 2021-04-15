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
import js.daos.CarDAO;
import js.dtos.CarDTO;
import js.dtos.CategoryDTO;
import js.dtos.ErrorDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class SearchController extends HttpServlet {
 private final static String SUCCESS = "SearchPageController";
    private final static String ERROR = "ToMainPageController";
    private final static Logger LOG = Logger.getLogger(SearchController.class);
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
            String category = request.getParameter("category");
            String name = request.getParameter("name");
            String pageNum = request.getParameter("pageNum");
            String gogo = request.getParameter("gogo");
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String quant = request.getParameter("quant");
            int qua = 0;
            Date startDate = null;
            Date endDate = null;
            boolean check = true;
            Controller con = new Controller();
            ErrorDTO error = new ErrorDTO();
            if (start != null && end != null && !"".equals(start) && !"".equals(start)) {
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
                if (startDate != null && endDate != null && startDate.compareTo(endDate) > 0) {
                    check = false;
                    error.setLine1("check in date can't > check out date");
                }
            } else {
                check = false;
                error.setLine1("enter Date");
            }
            if ("".equals(quant) || quant == null) {
                check = false;
                error.setLine2("enter quantity");
            } else if (!con.checkInt(quant)) {
                check = false;
                error.setLine2("quantity must be integer number");
            } else {
                qua = Integer.parseInt(quant);
            }
            CarDAO car = new CarDAO();
            List<CategoryDTO> listSub = car.getListSub();
            request.setAttribute("LIST_CATEGORY", listSub);
            if (check) {
                int page;
                if (pageNum == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(pageNum);
                }
                if (category == null) {
                    category = "";
                }
                if (name == null) {
                    name = "";
                }
                List<CarDTO> list;
                if (gogo != null) {
                    switch (gogo) {
                        case "go1":
                            category = "";
                            break;
                        case "go2":
                            name = "";
                            break;
                        default:
                            break;
                    }
                }
                int total = car.getTotal(name, category, startDate, endDate, qua);
                int totalPage = con.getTotalPage(total);
                list = car.getListItem(name, category, startDate, endDate, page, qua);
                if (page > totalPage) {
                    page = totalPage;
                }
                if (list != null) {
                    request.setAttribute("LIST", list);
                    request.setAttribute("TOTAL_PAGE", totalPage);
                    request.setAttribute("PAGE_NUM", page);
                } else {
                    error.setLine3("No car match to your request!");
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
