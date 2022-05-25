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
public class Usuarios {
    private String nombre, login, password;
    private int  idPerfil;
    private long idUsuario;

    public Usuarios() {
    }

    public Usuarios(String nombre, String login, String password, int idPerfil) {
        this.nombre = nombre;
        this.login = login;
        this.password = password;
        this.idPerfil = idPerfil;
    }

    public Usuarios(String nombre, String login, String password, int idPerfil, long idUsuario) {
        this.nombre = nombre;
        this.login = login;
        this.password = password;
        this.idPerfil = idPerfil;
        this.idUsuario = idUsuario;
    }
    public Usuarios(String nombre, String login, int idPerfil, long idUsuario) {
        this.nombre = nombre;
        this.login = login;
        this.idPerfil = idPerfil;
        this.idUsuario = idUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    
    
}
