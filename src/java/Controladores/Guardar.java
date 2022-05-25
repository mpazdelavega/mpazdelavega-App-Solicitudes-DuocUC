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
//import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "Guardar", urlPatterns = {"/Guardar"})
public class Guardar extends HttpServlet {

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
 
        int id = 0;
        int idflujo = 0;
        int idresponsable = 0;
        int idcreador = 0;
        String observaciones = "";
        
        Solicitud s = new Solicitud();
        DAOMail mail = new DAOMail();
        Alumno almod = new Alumno();
        
        DAOSolicitud daosol = new DAOSolicitud();
        HttpSession session = request.getSession(false);

            
            id = Integer.parseInt(request.getParameter("idsolicitud"));
            idflujo = Integer.parseInt(request.getParameter("tipoflujos"));
            idresponsable = Integer.parseInt(request.getParameter("idusuarioResponsable"));
            idcreador = Integer.parseInt(request.getParameter("idusuarioCreador"));
            observaciones = request.getParameter("observacion");
            DAOFlujoEstado fe = new DAOFlujoEstado();
            ArrayList<FlujoEstado> listaFe = fe.traerFlujoEstrados(idflujo);
            int idEstado = daosol.consultarEstado(id);
            
            
            for (FlujoEstado fl : listaFe) {
                if (fl.getIdEstdado() == idEstado) {
                    int update = listaFe.get(listaFe.indexOf(fl) + 1).getIdEstdado();
                    int penultimo = listaFe.get(listaFe.size()-2).getIdEstdado();
                    if (update == penultimo) {
                        daosol.actualizarSoli(idresponsable, update, observaciones, id);
                        daosol.pasarUsuarioCreador(id,idcreador);
                        daosol.pasarUsuarioResponsable(id,idresponsable);
                    } else {
                        daosol.actualizarSoli(idresponsable, update, observaciones, id);
                    }
                }

            }
            
            request.getRequestDispatcher("Solicitud.jsp?tiposol=asignadas&idsolicitud=" + id).forward(request, response);

        

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
