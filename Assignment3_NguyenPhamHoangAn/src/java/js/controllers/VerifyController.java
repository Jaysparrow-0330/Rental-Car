/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import js.daos.UserDAO;
import js.dtos.ErrorDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class VerifyController extends HttpServlet {
  private final static String SUCCESSOK = "ToLoginPageController";
    private final static String SUCCESS = "ToVerifyPageController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(VerifyController.class);
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
            String code = request.getParameter("code");
            String email = request.getParameter("email");
            UserDAO dao = new UserDAO();
            ErrorDTO error = new ErrorDTO();
            boolean check = true;
            if (code == null || "".equals(code) || email == null || "".equals(email)) {
                check = false;
                error.setLine1("please fill all");
            } else {
                if (!"".equals(dao.checkCode(email, code, true))) {
                    check = false;
                    error.setLine1("this user already valid");
                } else if ("".equals(dao.getCodeFromID(email))) {
                    check = false;
                    error.setLine1("this email is not created");
                } else {
                    if (!"".equals(dao.checkCode(email, code, false))) {
                        dao.updateUser(email, true);
                        error.setLine1("success");
                    } else {
                        check = false;
                        error.setLine1("invalid code");
                    }
                }
            }
            if (check) {
                url = SUCCESSOK;
            } else {
                url = SUCCESS;
            }
            request.setAttribute("ERROR", error);
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
