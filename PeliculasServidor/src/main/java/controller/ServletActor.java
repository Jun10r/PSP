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
import services.ServicioActor;
import utilidades.ConstantesClaves;
import static utilidades.ConstantesClaves.CLAVE_ACTORES;
import static utilidades.ConstantesClaves.CLAVE_OBJECT;
import static utilidades.ConstantesClaves.PARAMETRO_ACTORES;
import static utilidades.ConstantesClaves.PARAMETRO_POST;
import utilidades.PasswordHash;

/**
 *
 * @author Junior
 */
public class ServletActor extends HttpServlet {

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
        ServicioActor sa = new ServicioActor();
        if (op != null) {
            switch (op) {
                case "get":
                    request.setAttribute("send", sa.getAllActors());
                    break;
                case "getMovie":

                    String codRef = request.getParameter("codRef");
                    request.setAttribute("send", sa.getAllActorsByMovie(Integer.parseInt(codRef)));
                    break;
                case "getActor":
                    String name = request.getParameter("name");
                    request.setAttribute("send", sa.getActor(name));
                    break;
                case "insert":
                    //String id = request.getParameter("id");
                    Actor a = (Actor) request.getAttribute("request");
                    if (sa.inserted(a)) {
                        response.getWriter().print("OK");
                    }
                    break;
                case "insertActuan":
                    String peli = request.getParameter("id");
                    Actor aInsertActuan = (Actor) request.getAttribute("request");

                    if (sa.insertActuan(aInsertActuan, Integer.parseInt(peli))) {
                        response.getWriter().print("OK");
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
