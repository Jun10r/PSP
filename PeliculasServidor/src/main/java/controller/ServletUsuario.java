/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;
import pojos.Usuario;
import services.ServicioUsuario;
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_LOGIN;
import static utilidades.ConstantesClaves.CLAVE_REGISTRO;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

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
        String op = request.getParameter("op");
        ServicioUsuario sv = new ServicioUsuario();

        if (op != null) {
            switch (op) {
                case "login":

                    ObjectMapper mapLogin = new ObjectMapper();
                    byte[] usuarioB64 = Base64.decodeBase64(request.getParameter(PARAMETRO_POST).getBytes("UTF-8"));
                    try {
                        Usuario u = mapLogin.readValue(
                                PasswordHash.descifra(usuarioB64, CLAVE_LOGIN),
                                new TypeReference<Usuario>() {
                        });

                        Usuario userBD = sv.loginUser(u); // Usuario de Base de Datos

                        boolean validated = PasswordHash.validatePassword(u.getPassword(), userBD.getPassword());
                        if (validated) {
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("login", "OK");
                            response.getWriter().print("TRUE");
                        } else {
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("login", "FALSE");
                            response.getWriter().print("FALSE");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(ServletUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case "registra":
                    ObjectMapper mapper = new ObjectMapper();
                    byte[] b64 = Base64.decodeBase64(request.getParameter(PARAMETRO_POST).getBytes("UTF-8"));
                    try {
                        Usuario u = mapper.readValue(
                                PasswordHash.descifra(b64, CLAVE_REGISTRO),
                                new TypeReference<Usuario>() {
                        });
                        u.setPassword(PasswordHash.createHash(u.getPassword()));
                        if (sv.insertUser(u)) {
                            response.getWriter().print("OK");
                        }

                    } catch (Exception ex) {
                        Logger.getLogger(ServletUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
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
