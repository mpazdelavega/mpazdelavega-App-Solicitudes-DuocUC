/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.DAOMail;
import DAO.DAOMotivos;
import DAO.DAOSolicitud;
import Modelo.Alumno;
import Modelo.Solicitud;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro
 */
@WebServlet(name = "Rechazar", urlPatterns = {"/Rechazar"})
public class Rechazar extends HttpServlet {

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
        
        String id = request.getParameter("idsol");
        String idUsuCrea = request.getParameter("creador");
        String idUsuResp = request.getParameter("responsable");
        String cadena = request.getParameter("observacion");
        int motivo = Integer.parseInt(request.getParameter("motivo"));
        //6 es el valor del ID correspondiente a un estado de "RECHAZADO".
        int estado = 6;
        System.out.println(cadena);
        
        DAOMotivos moti = new DAOMotivos();
        DAOSolicitud daosol = new DAOSolicitud();
        DAOMail mail = new DAOMail();
        Alumno almod = new Alumno();
        Solicitud s = new Solicitud();
        
        s.setIdflujo(Integer.parseInt(request.getParameter("tipoflujo")));
        s.setIdSolicitud(Integer.parseInt(id));
        s.setIdMotivo(motivo);
        s.setObservacion(cadena);
        s.setIdUsuarioCreador(Integer.valueOf(idUsuCrea));
        s.setIdUsuarioResponsable(Integer.valueOf(idUsuResp));
        s.setDescripcion(request.getParameter("descripcion"));
        s.setCodEstado(estado);
        
        almod.setNOMBRE_ALUMNO(request.getParameter("nombrealumno"));
        almod.setRUT_ALUMNO(request.getParameter("rut"));
        almod.setID_JORNADA(Integer.parseInt(request.getParameter("idjornada")));
        almod.setCARRERA_ALUMNO(request.getParameter("carrera"));
        almod.setTELEFONO(request.getParameter("telefono"));
        almod.setCORREO_ALUMNO(request.getParameter("correoalumno"));
        
        
        HttpSession session = request.getSession(false);
        boolean respuesta = daosol.rechazarSolicitud(motivo, cadena, Integer.valueOf(id));
        
        if (respuesta) {
           boolean observacion = daosol.pasarObservacion(cadena, Integer.valueOf(id));
           boolean motiv = moti.pasarMotivos(motivo, Integer.valueOf(id));
           mail.sendEmailRechazar(almod, s);
           request.getRequestDispatcher("home.jsp").include(request, response);
        }
        else{
            request.getRequestDispatcher("Solicitud.jsp?tiposol=asignadas&idsolicitud=" + id).forward(request, response);
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
