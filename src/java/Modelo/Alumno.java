/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Pancho
 */
public class Alumno {
    
    private int ID_ALUMNO, ID_JORNADA ;
    private String NOMBRE_ALUMNO, RUT_ALUMNO, CARRERA_ALUMNO, CORREO_ALUMNO,TELEFONO; 

    public Alumno() {
    }

    public int getID_ALUMNO() {
        return ID_ALUMNO;
    }

    public void setID_ALUMNO(int ID_ALUMNO) {
        this.ID_ALUMNO = ID_ALUMNO;
    }

    public int getID_JORNADA() {
        return ID_JORNADA;
    }

    public void setID_JORNADA(int ID_JORNADA) {
        this.ID_JORNADA = ID_JORNADA;
    }

    public String getNOMBRE_ALUMNO() {
        return NOMBRE_ALUMNO;
    }

    public void setNOMBRE_ALUMNO(String NOMBRE_ALUMNO) {
        this.NOMBRE_ALUMNO = NOMBRE_ALUMNO;
    }

    public String getRUT_ALUMNO() {
        return RUT_ALUMNO;
    }

    public void setRUT_ALUMNO(String RUT_ALUMNO) {
        this.RUT_ALUMNO = RUT_ALUMNO;
    }

    public String getCARRERA_ALUMNO() {
        return CARRERA_ALUMNO;
    }

    public void setCARRERA_ALUMNO(String CARRERA_ALUMNO) {
        this.CARRERA_ALUMNO = CARRERA_ALUMNO;
    }

    public String getCORREO_ALUMNO() {
        return CORREO_ALUMNO;
    }

    public void setCORREO_ALUMNO(String CORREO_ALUMNO) {
        this.CORREO_ALUMNO = CORREO_ALUMNO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public Alumno(int ID_ALUMNO, int ID_JORNADA, String NOMBRE_ALUMNO, String RUT_ALUMNO, String CARRERA_ALUMNO, String CORREO_ALUMNO, String TELEFONO) {
        this.ID_ALUMNO = ID_ALUMNO;
        this.ID_JORNADA = ID_JORNADA;
        this.NOMBRE_ALUMNO = NOMBRE_ALUMNO;
        this.RUT_ALUMNO = RUT_ALUMNO;
        this.CARRERA_ALUMNO = CARRERA_ALUMNO;
        this.CORREO_ALUMNO = CORREO_ALUMNO;
        this.TELEFONO = TELEFONO;
    }
}
