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
public class Estado {
    private int cod_estado;
    private String estado;

    public Estado(int cod_estado, String estado) {
        this.cod_estado = cod_estado;
        this.estado = estado;
    }

    public Estado() {
    }

    public int getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(int cod_estado) {
        this.cod_estado = cod_estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
