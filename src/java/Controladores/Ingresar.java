/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.DAOSolicitud;
import DAO.DAOUsuario;
import Modelo.Solicitud;
import Modelo.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Silvana
 */
@WebServlet(name = "Ingresar", urlPatterns = {"/Ingresar"})
public class Ingresar extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        String correo = request.getParameter("correo");
        String pass = request.getParameter("password");
        DAO.DAOUsuario dao = new DAOUsuario();
        
        if (dao.validarUsuario(correo, pass)) {
            DAOSolicitud sol = new DAOSolicitud();
            
            HttpSession session = request.getSession(true); // reuse existing
            
            Usuarios user = dao.traerUsuario(correo, pass);
            session.setAttribute("nombreusuario", user.getNombre());
            session.setAttribute("usuario", String.valueOf(user.getIdUsuario()));
            
            session.setAttribute("notificaciones", sol.consultarNotificaciones(String.valueOf(user.getIdUsuario())));
            ArrayList<Solicitud> ar = (ArrayList<Solicitud>)session.getAttribute("notificaciones");
            int cant = ar.size();
            session.setAttribute("cantNotif",cant);
            request.getRequestDispatcher("home.jsp").include(request, response);
        } else {    
            boolean error = true;
            request.setAttribute("Error", error);
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
