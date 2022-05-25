/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author Silvana
 */
public class Solicitud {
    private int idSolicitud;
    private String descripcion;
    private java.sql.Date fechaSolicitud;
    private String nombreSolicitud;
    private int idUsuarioCreador;
    private int idUsuarioResponsable;
    private int codTipoDocumento;
    private String otro;
    private int codEstado;
    private int idflujo;
    private int idAlumno;
    private int IdMotivo;
    private String Observacion ;
    private int NumTicket;

    public int getNumTicket() {
        return NumTicket;
    }

    public void setNumTicket(int NumTicket) {
        this.NumTicket = NumTicket;
    }

    public int getIdMotivo() {
        return IdMotivo;
    }

    public void setIdMotivo(int IdMotivo) {
        this.IdMotivo = IdMotivo;
    }
    
    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Solicitud() {
    }

    public Solicitud(int idSolicitud, String descripcion, Date fechaSolicitud, String nombreSolicitud, int idUsuarioCreador, int idUsuarioResponsable, int codTipoDocumento, String otro, int codEstado, int idflujo, int idAlumno, int IdMotivo ,String Observacion, int NumTicket) {
        this.idSolicitud = idSolicitud;
        this.descripcion = descripcion;
        this.fechaSolicitud = fechaSolicitud;
        this.nombreSolicitud = nombreSolicitud;
        this.idUsuarioCreador = idUsuarioCreador;
        this.idUsuarioResponsable = idUsuarioResponsable;
        this.codTipoDocumento = codTipoDocumento;
        this.otro = otro;
        this.codEstado = codEstado;
        this.idflujo = idflujo;
        this.idAlumno = idAlumno;
        this.Observacion = Observacion;
        this.IdMotivo = IdMotivo;
        this.NumTicket = NumTicket;
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getNombreSolicitud() {
        return nombreSolicitud;
    }

    public void setNombreSolicitud(String nombreSolicitud) {
        this.nombreSolicitud = nombreSolicitud;
    }

    public int getIdUsuarioCreador() {
        return idUsuarioCreador;
    }

    public void setIdUsuarioCreador(int idUsuarioCreador) {
        this.idUsuarioCreador = idUsuarioCreador;
    }

    public int getIdUsuarioResponsable() {
        return idUsuarioResponsable;
    }

    public void setIdUsuarioResponsable(int idUsuarioResponsable) {
        this.idUsuarioResponsable = idUsuarioResponsable;
    }

    public int getCodTipoDocumento() {
        return codTipoDocumento;
    }

    public void setCodTipoDocumento(int codTipoDocumento) {
        this.codTipoDocumento = codTipoDocumento;
    }

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public int getIdflujo() {
        return idflujo;
    }

    public void setIdflujo(int idflujo) {
        this.idflujo = idflujo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    

}
