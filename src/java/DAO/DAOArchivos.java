/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import interfaces.CRUD;
import Modelo.Archivos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Alvaro
 */
public class DAOArchivos implements CRUD<Archivos> {
    Conexion c = new Conexion();
    //private Connection conn = new Conexion().Conectar();

    @Override
    public boolean crear(Archivos o) {
        boolean respuesta = false;
        Connection conn = c.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into archivos values(default,?,?,?,?)");
            ps.setInt(1, o.getIdSolicitud());
            ps.setString(2, o.getDescripcion());
            ps.setString(3, o.getNombreArchivo());
            ps.setString(4,o.getRuta());
            ps.execute();
            respuesta = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return respuesta;
    }

    @Override
    public boolean eliminar(Archivos o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Archivos o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Archivos> listarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Archivos obtenerArchivo(int idsol){
        Archivos archiv = new Archivos();
        Connection conn = c.Conectar();
        try {
         PreparedStatement ps = conn.prepareStatement("select * from archivos where id_solicitud = ?");
         ps.setInt(1, idsol);
         ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                archiv.setDescripcion(rs.getString("DESCRIPCION"));
                archiv.setIdArchivo(rs.getInt("ID_ARCHIVO"));
                archiv.setNombreArchivo(rs.getString("NOMBRE"));
                archiv.setIdSolicitud(rs.getInt("ID_SOLICITUD"));
                archiv.setRuta(rs.getString("RUTA"));        
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return archiv;
    }
}
