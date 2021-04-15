/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package js.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import js.daos.UserDAO;
import js.dtos.ErrorDTO;
import js.dtos.UserDTO;
import js.ultils.MailSender;
import org.apache.log4j.Logger;

/**
 *
 * @author jack3
 */
public class CreateUserController extends HttpServlet {
private final static String SUCCESS = "ToRegisterPageController";
    private final static String SUCCESSOK = "ToVerifyPageController";
    private final static String ERROR = "ErrorController";
    private final static Logger LOG = Logger.getLogger(CreateUserController.class);
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
            String email = request.getParameter("email");
            String userName = request.getParameter("userName");
            String userPass = request.getParameter("userPass");
            String rePass = request.getParameter("rePass");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            Controller con = new Controller();
            ErrorDTO error = new ErrorDTO("", "", "", "", "", "", "");
            UserDAO dao = new UserDAO();
            boolean check = true;
            if (userName.length() > 30) {
                check = false;
                error.setLine2("max length is 30");
            }
            if (!con.checkString(userName)) {
                check = false;
                error.setLine2("English only");
            }
            if ("".equals(userName.trim())) {
                check = false;
                error.setLine2("userName can't null");
            }
            if (address.length() > 100) {
                check = false;
                error.setLine4("max length is 10");
            }
            if (!con.checkString(address)) {
                check = false;
                error.setLine4("English only");
            }
            if ("".equals(address.trim())) {
                check = false;
                error.setLine4("address can't null");
            }
            if (!phone.matches("[0-9]{10,15}")) {
                check = false;
                error.setLine3("try other phone number");
            }
            if ("".equals(phone.trim())) {
                check = false;
                error.setLine3("phone can't null");
            }
            if (!con.checkEmail(email)) {
                check = false;
                error.setLine1("try other email");
            }
            if ("".equals(email.trim())) {
                check = false;
                error.setLine1("email can't null");
            }
            if (!dao.getCodeFromID(email).equals("")) {
                check = false;
                error.setLine1("email have been used");
            }
            if (!con.checkString(userPass)) {
                check = false;
                error.setLine5("try other password");
            }
            if (userPass.length() > 20) {
                check = false;
                error.setLine5("max length is 20");
            }
            if ("".equals(userPass.trim())) {
                check = false;
                error.setLine5("password can't null");
            }
            if ("".equals(rePass.trim())) {
                check = false;
                error.setLine6("re password can't null");
            }
            if (!userPass.equals(rePass)) {
                check = false;
                error.setLine6("re password is not match");
            }
            if (check) {
                long millis = System.currentTimeMillis();
                Date createDate = new Date(millis);
                String code = con.getAlphaNumericString(6);
                MailSender mail = new MailSender();
                if (mail.send(email, code)) {
                    UserDTO user = new UserDTO(email, userPass, userName, code, phone, address, createDate, false);
                    dao.newUser(user);
                    url = SUCCESSOK;
                }
                else{
                    error.setLine7("Can not send email");
                }
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
