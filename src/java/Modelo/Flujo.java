/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Silvana
 */
public class Flujo {
    private int idFlujo;
    private String nombreFlujo;

    public Flujo(int idFlujo, String nombreFlujo) {
        this.idFlujo = idFlujo;
        this.nombreFlujo = nombreFlujo;
    }

    public Flujo() {
    }

    
    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public String getNombreFlujo() {
        return nombreFlujo;
    }

    public void setNombreFlujo(String nombreFlujo) {
        this.nombreFlujo = nombreFlujo;
    }
    
}
