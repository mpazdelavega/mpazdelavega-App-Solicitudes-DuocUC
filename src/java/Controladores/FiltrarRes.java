/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.DAOUsuario;
import Modelo.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author manue
 */
@WebServlet(name = "FiltrarRes", urlPatterns = {"/FiltrarRes"})
public class FiltrarRes extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        response.setContentType("text/html;charset=UTF-8");
//        try(PrintWriter out = response.getWriter()){
//            String id  = request.getParameter("id");
//            int idFlujo = Integer.parseInt(id);
//            out.println("Porfavor FUNCIONAAA"+idFlujo);
//        }
//        String resultado = "<option value='0'>Seleccione un Responsable</option>";
//
//        DAOUsuario daousu = new DAOUsuario();
//        ArrayList<Usuarios> listaUsuarios = daousu.filtro(idFlujo);
//        if(listaUsuarios.size() > 0){
//             resultado = "<option value='-1'>Seleccione un Responsable</option>";
//             for (Usuarios usu : listaUsuarios) {
//
//             resultado = resultado + "<option value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>";
//
//         }
//        }
//        try(PrintWriter out = response.getWriter()) {
//            out.println(resultado);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String idFlujoString = request.getParameter("id");
        int idFlujo = Integer.parseInt(idFlujoString);
        String resultado = "<option value='' disabled selected>Seleccione un Responsable</option>";
        DAOUsuario daousu = new DAOUsuario();
        ArrayList<Usuarios> listaUsuarios = daousu.filtro(idFlujo);
        if (listaUsuarios.size() > 0) {
            for (Usuarios usu : listaUsuarios) {
                resultado = resultado + "<option value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>";
            }
        }
        try (PrintWriter out = response.getWriter()) {
            out.println(resultado);
        }
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