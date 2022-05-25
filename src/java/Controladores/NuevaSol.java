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

import DAO.DAOAlumno;
import DAO.DAOArchivos;
import DAO.DAOFlujoEstado;
import DAO.DAOMail;
import DAO.DAOSolicitud;
import Modelo.Alumno;
import Modelo.Archivos;
import Modelo.FlujoEstado;
import Modelo.Solicitud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Silvana
 */
@WebServlet(name = "NuevaSol", urlPatterns = {"/NuevaSol"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class NuevaSol extends HttpServlet {

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
        
        DAOSolicitud daosol = new DAOSolicitud();
        HttpSession session = request.getSession(false);
        
        
        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
        } else {

            int idUsuario = Integer.parseInt(session.getAttribute("usuario").toString());
           
            Solicitud s = new Solicitud();
            DAO.DAOFlujoEstado fe = new DAOFlujoEstado();
            DAOAlumno daoal = new DAOAlumno();
            Modelo.Alumno almod = new Alumno();
            
            s.setCodEstado(1);
            s.setDescripcion(request.getParameter("descripcion"));
            s.setIdUsuarioCreador(idUsuario);
            
            int idflujo = Integer.parseInt(request.getParameter("tipoflujo"));
            FlujoEstado flujo = fe.traerFlujoEstrado(1, idflujo);
            
            s.setIdUsuarioResponsable(Integer.parseInt(request.getParameter("idusuario")));
            s.setNombreSolicitud(request.getParameter("nombresolicitud"));
            s.setOtro("");
            s.setIdflujo(flujo.getIdFlujo());
            
            
            almod.setNOMBRE_ALUMNO(request.getParameter("nombrealumno"));
            almod.setRUT_ALUMNO(request.getParameter("rutalumno"));
            almod.setCARRERA_ALUMNO(request.getParameter("carreraalumno"));
            almod.setID_JORNADA(Integer.parseInt(request.getParameter("idjornada")));
            almod.setCORREO_ALUMNO(request.getParameter("correoalumno"));
            almod.setTELEFONO(request.getParameter("telefono"));
            
            int idal = daoal.crearConid(almod);
            s.setIdAlumno(idal);
            int idsolicitud = daosol.crearConid(s);
            s.setIdSolicitud(idsolicitud);
            DAOMail mail = new DAOMail();
            
            if (idsolicitud>0) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + "docs";
                String getPath = getServletContext().getContextPath() + File.separator + "docs";
                File uploadDir = new File(uploadPath);
                mail.sendEmail(almod, s);
            

            /*
                LA CARGA DE ARCHIVOS DEBERIA SER MODULARIZADA 
                POR SI SE LLEGA A NECESITAR EN ALGUN OTRO MOMENTO
            */ 
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            Part filePart = request.getPart("file"); // Obtiene el Archivo
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Obtiene el Nombre del Archivo
            String FormatName = fileName.replaceAll(" ","");

            /// EXTRAER ARCHIVO Y COPIAR        
            InputStream fileStream = filePart.getInputStream();

            OutputStream outputStream = null;
            try {
                File file = new File(uploadPath + File.separator + FormatName);
                
                outputStream = new FileOutputStream(file);

                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = fileStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                DAO.DAOArchivos daoarchivo = new DAOArchivos();
                Modelo.Archivos archivo = new Archivos();
                
                archivo.setIdSolicitud(idsolicitud);
                archivo.setNombreArchivo(FormatName);
                archivo.setDescripcion("");
                archivo.setRuta(getPath + File.separator + FormatName);
                
                daoarchivo.crear(archivo);
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }
                request.getRequestDispatcher("solicitudespropias.jsp").include(request, response);
            }
            else{
                request.getRequestDispatcher("home.jsp").forward(request, response);
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
