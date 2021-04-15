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
import js.daos.UserDAO;
import js.dtos.ErrorDTO;
import js.dtos.UserDTO;
import js.ultils.VerifyUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class LoginController extends HttpServlet {
private final static String HOME = "SearchPageController";
    private final static String INPUT_CODE = "ToVerifyPageController";
    private final static String ERROR = "ToLoginPageController";
    private final static Logger LOG = Logger.getLogger(LoginController.class);
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
            ss.removeAttribute("ERROR");
            String email = request.getParameter("email");
            String userPass = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO user = dao.checkLogin(email, userPass);
            ErrorDTO error = new ErrorDTO("", "", "", "", "", "", "");
            boolean check = true;
            if (userPass == null || "".equals(userPass)) {
                check = false;
                error.setLine1("please enter password");
            }
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean valid = VerifyUtils.verify(gRecaptchaResponse);
            if (!valid) {
                check = false;
                error.setLine1("captcha please");
            }
            if (check) {
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", user);
                    if (user.getCode() != null && !user.isStatus()) {
                        url = INPUT_CODE;
                    } else {
                        url = HOME;
                    }
                } else {
                    error.setLine1("This emails or password maybe wrong, try again");
                    ss.setAttribute("ERROR", error);
                }
            } else {
                ss.setAttribute("ERROR", error);
            }
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
