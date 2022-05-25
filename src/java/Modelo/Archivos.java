/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Alvaro
 */
public class Archivos {
        private int idArchivo;
        private int idSolicitud;
        private String descripcion;
        private String nombreArchivo;
        private String ruta;

    public Archivos(int idSolicitud, String descripcion, String nombreArchivo, String ruta) {
        this.idSolicitud = idSolicitud;
        this.descripcion = descripcion;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
    }

    public Archivos() {
    }

    public int getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(int idArchivo) {
        this.idArchivo = idArchivo;
    }

    
    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
        
        
}
