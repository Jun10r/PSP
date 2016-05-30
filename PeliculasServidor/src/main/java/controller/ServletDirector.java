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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import pojos.Actor;
import pojos.Director;
import services.ServicioDirector;
import static utilidades.ConstantesClaves.CLAVE_ACTORES;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class ServletDirector extends HttpServlet {

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
        
        /*if(getSesion().getAtribute("clave","valor")){
        }else{
            fordward(); error
        };*/
        
        
        ServicioDirector sd = new ServicioDirector();
        String op = request.getParameter("op");
        // Director d = new Director("junior", "Lizama");
        if (op != null) {
            switch (op) {
                case "get":
                    request.setAttribute("send", sd.getAllDirectores());
                    break;
                case "getMovie":
                    String codRef = request.getParameter("codRef");
                    request.setAttribute("send", sd.getAllDirectoresByMovie(Integer.parseInt(codRef)));
                    break;
                case "insert":
                    /*
                    String id = request.getParameter("id");
                    ObjectMapper mapinsert = new ObjectMapper();
                    byte[] b64a = Base64.decodeBase64(request.getParameter(PARAMETRO_POST).getBytes("UTF-8"));
                    try {
                        Actor a = mapinsert.readValue(PasswordHash.descifra(b64a, CLAVE_ACTORES), new TypeReference<Actor>() {
                        });
                        if (sd.inserted(a,Integer.parseInt(id))) {
                            response.getWriter().print("OK");
                        }
                    } catch (Exception e) {
                        Logger.getLogger(ServletPelicula.class.getName()).log(Level.SEVERE, null, e);
                    }
                    */
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
