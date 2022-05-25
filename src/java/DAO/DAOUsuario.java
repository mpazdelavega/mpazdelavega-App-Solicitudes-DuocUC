/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Modelo.Usuarios;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Silvana
 */
public class DAOUsuario implements CRUD<Usuarios> {

    private static Conexion co;
    
    private static String sql_selectAll = "SELECT * FROM usuario order BY NOMBRE ASC ";
    private static String sql_insert = "INSERT INTO usuario (ID_USUARIO,NOMBRE, LOGIN, PASSWORD, ID_PERFIL) VALUES (?,?,?,?,?)";
    private static String sql_delete = "delete from usuario where id_usuario = ?";
    private static String sql_selectUsuario = "select * from usuario where login = ? and password = ?";
    private static String sql_selectUsuario2 = "select * from usuario where id_usuario = ?";
    private static String sql_selectUsuario4 = "SELECT * FROM usuario u INNER JOIN flujo_responsable f ON f.ID_USUARIO = u.ID_USUARIO INNER JOIN flujo fl  ON fl.id_flujo = f.ID_FLUJO WHERE fl.id_flujo = ?";
    //select * from flujo_estado f join flujo fl on f.id_flujo = fl.id_flujo JOIN estado e on e.COD_ESTADO = f.id_estado JOIN usuario u on u.ID_USUARIO = f.id_responsable;
    private static String sql_validarUsuario = "select * from webdocs.usuario where login = ? and password = ?   ";
    private static String sql_update = "update usuario set nombre = ? , login = ? , password = ? ,  id_perfil = ? where id_usuario = ?";
    
    /**
     *
     * @param login
     * @param pass
     * @return
     */
    public Usuarios traerUsuario(String login, String pass) {
        Usuarios user = new Usuarios();
        ResultSet rs = null;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuario);
            ps.setString(1, login);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = new Usuarios();
                user.setNombre(rs.getString("NOMBRE"));
                user.setLogin(rs.getString("LOGIN"));
                user.setPassword(rs.getString("PASSWORD"));
                user.setIdPerfil(rs.getInt("ID_PERFIL"));
                user.setIdUsuario(rs.getInt("ID_USUARIO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    /**
     *
     * @param id
     * @return
     */
    public Usuarios traerUsuario(int id) {
        Usuarios user = new Usuarios();
        ResultSet rs = null;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuario2);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setNombre(rs.getString("NOMBRE"));
                user.setLogin(rs.getString("LOGIN"));
                user.setIdPerfil(rs.getInt("ID_PERFIL"));
                user.setIdUsuario(rs.getInt("ID_USUARIO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public Usuarios traerUsuarioResponsable(int id) {
        Usuarios user = new Usuarios();
        ResultSet rs = null;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT s.NOMBRE FROM usuario s INNER JOIN solicitud sol on s.ID_USUARIO = sol.ID_USUARIO_RESPONSABLE WHERE sol.ID_USUARIO_RESPONSABLE = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setNombre(rs.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
    
    public ArrayList<Usuarios> filtro(int tipoflujo) {
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        ResultSet rs = null;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuario4);
            ps.setInt(1, tipoflujo);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Usuarios user = new Usuarios();
                user.setNombre(rs.getString("NOMBRE"));
                user.setIdPerfil(rs.getInt("ID_PERFIL"));
                user.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuarios.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());         
        } 
        return usuarios;
    }

    /**
     *
     * @param correo
     * @param pass
     * @return
     */
    public boolean validarUsuario(String correo, String pass) {
        boolean retorno = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            co = new Conexion();
            conn = co.Conectar();
            ps = conn.prepareStatement(sql_validarUsuario);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            
            int cont = 0;
            while (rs.next()) {
                cont++;
                if (cont == 1) {
                    retorno = true;
                }
            }
            try{
                conn.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean crear(Usuarios o) {
        boolean retorno = false;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_insert);
            ps.setLong(1, o.getIdUsuario());
            ps.setString(2, o.getNombre());
            ps.setString(3, o.getLogin());
            ps.setString(4, o.getPassword());
            ps.setInt(5, o.getIdPerfil());
            ps.execute();

            retorno = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean eliminar(Usuarios o) {
        boolean respuesta = false;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_delete);
            ps.setLong(1, o.getIdUsuario());
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean modificar(Usuarios o) {
        boolean retornoBol = false;
        Connection conn = co.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_update);
            ps.setString(1, o.getNombre());
            ps.setString(2, o.getLogin());
            ps.setString(3, o.getPassword());
            ps.setInt(4, o.getIdPerfil());
            ps.setLong(5, o.getIdUsuario());
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                retornoBol = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            retornoBol = false;
        }
        return retornoBol;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Usuarios> listarTodo() {
        ArrayList<Usuarios> usuarios = new ArrayList<>();
        Connection conn = co.Conectar();
        
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql_selectAll);
            while (rs.next()) {
                Usuarios user = new Usuarios();
                user.setNombre(rs.getString("NOMBRE"));
                user.setIdPerfil(rs.getInt("ID_PERFIL"));
                user.setIdUsuario(rs.getInt("ID_USUARIO"));
                usuarios.add(user);
            }
            return usuarios;

        } catch (SQLException e) {
            System.out.println(e.getMessage());         
        } 
        return usuarios;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean eliminar(int o) {
        boolean respuesta = false;
        Connection conn = co.Conectar();
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql_delete);
            ps.setLong(1, o);
            if (ps.executeUpdate() > 0) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

}
