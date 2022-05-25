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
public class FlujoEstado {
    private int idEstdado;
    private int idFlujo;
    private int idResponsable;

    public FlujoEstado(int idEstdado, int idFlujo, int idResponsable) {
        this.idEstdado = idEstdado;
        this.idFlujo = idFlujo;
        this.idResponsable = idResponsable;
    }

    public FlujoEstado() {
    }
    

    public int getIdEstdado() {
        return idEstdado;
    }

    public void setIdEstdado(int idEstdado) {
        this.idEstdado = idEstdado;
    }

    public int getIdFlujo() {
        return idFlujo;
    }

    public void setIdFlujo(int idFlujo) {
        this.idFlujo = idFlujo;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }
    
}
