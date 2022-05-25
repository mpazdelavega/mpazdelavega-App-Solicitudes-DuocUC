/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.DAOFlujoEstado;
import DAO.DAOMail;
import DAO.DAOSolicitud;
import Modelo.Alumno;
import Modelo.FlujoEstado;
import Modelo.Solicitud;
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
 * @author Felipe
 */
@WebServlet(name = "EmailAprobar", urlPatterns = {"/EmailAprobar"})
public class EmailAprobar extends HttpServlet {

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
        int codEstado = 0;
        int idflujo = 0;
        DAOMail mail = new DAOMail();
        Alumno almod = new Alumno();
        Solicitud s = new Solicitud();
        DAOSolicitud daosol = new DAOSolicitud();
        DAOFlujoEstado fe = new DAOFlujoEstado();
        idflujo = Integer.parseInt(request.getParameter("tipoflujo"));
        ArrayList<FlujoEstado> listaFe = fe.traerFlujoEstrados(idflujo);
        int idEstado = daosol.consultarEstado(Integer.parseInt(id));
        for (FlujoEstado fl : listaFe) {
                if (fl.getIdEstdado() == idEstado) {
                    int update = listaFe.get(listaFe.indexOf(fl) + 1).getIdEstdado();
                    codEstado=update;
                }

            }
        
        s.setIdflujo(Integer.parseInt(request.getParameter("tipoflujo")));
        s.setIdSolicitud(Integer.parseInt(id));
        s.setIdUsuarioCreador(Integer.valueOf(idUsuCrea));
        s.setIdUsuarioResponsable(Integer.valueOf(idUsuResp));
        s.setDescripcion(request.getParameter("descripcion"));
        s.setCodEstado(codEstado);
        
        almod.setNOMBRE_ALUMNO(request.getParameter("nombrealumno"));
        almod.setRUT_ALUMNO(request.getParameter("rut"));
        almod.setID_JORNADA(Integer.parseInt(request.getParameter("idjornada")));
        almod.setCARRERA_ALUMNO(request.getParameter("carrera"));
        almod.setTELEFONO(request.getParameter("telefono"));
        almod.setCORREO_ALUMNO(request.getParameter("correoalumno"));
        
        mail.sendEmailAprobar(almod, s);
        
        
        
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
