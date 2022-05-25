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
public class Jornada {
    
    private int id_jornada; 
    private String nom_jornada;

    public Jornada() {
    }

    public Jornada(int id_jornada, String nom_jornada) {
        this.id_jornada = id_jornada;
        this.nom_jornada = nom_jornada;
    }

    public int getId_jornada() {
        return id_jornada;
    }

    public void setId_jornada(int id_jornada) {
        this.id_jornada = id_jornada;
    }

    public String getNom_jornada() {
        return nom_jornada;
    }

    public void setNom_jornada(String nom_jornada) {
        this.nom_jornada = nom_jornada;
    }
    
    
}
