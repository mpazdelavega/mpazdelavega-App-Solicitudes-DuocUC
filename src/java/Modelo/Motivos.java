/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Felipe
 */
public class Motivos {
   private int IdMotivo ; 
   private String Motivo;
   
   public Motivos() {
    }

    public Motivos(int IdMotivo, String Motivo) {
        this.IdMotivo = IdMotivo;
        this.Motivo = Motivo;
    }
   

    public int getIdMotivo() {
        return IdMotivo;
    }

    public void setIdMotivo(int IdMotivo) {
        this.IdMotivo = IdMotivo;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    
   
}

