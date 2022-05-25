/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Modelo.Alumno;
import Modelo.Archivos;
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Modelo.Jornada;

/**
 *
 * @author Pancho
 */
public class DAOAlumno {
    Conexion c = new Conexion();
    //private Connection conn = c.Conectar();
    
    public boolean crear(Alumno a) {
        boolean respuesta = false;
        Connection conn = c.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into alumno values(default,?,?,?,?,?,?)");
            ps.setString(1, a.getNOMBRE_ALUMNO());
            ps.setString(2, a.getRUT_ALUMNO());
            ps.setString(3, a.getCARRERA_ALUMNO());
            ps.setInt(4, a.getID_JORNADA());
            ps.setString(5, a.getCORREO_ALUMNO());
            ps.setString(6, a.getTELEFONO());
            ps.execute();
            respuesta = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            c.cerrarConexion(conn);
        }
        return respuesta;
    }

    public int crearConid(Alumno a) {
        int id = 0;
        Connection conn = c.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("insert into alumno values(default,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNOMBRE_ALUMNO());
            ps.setString(2, a.getRUT_ALUMNO());
            ps.setString(3, a.getCARRERA_ALUMNO());
            ps.setInt(4, a.getID_JORNADA());
            ps.setString(5, a.getCORREO_ALUMNO());
            ps.setString(6, a.getTELEFONO());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            id = rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            c.cerrarConexion(conn);
        }
        return id;
    }

    public ArrayList<Jornada> listarTodo() {
        ArrayList<Jornada> jornadas = new ArrayList<>();
        Connection conn = c.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from jornada;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Jornada jor = new Jornada();
                jor.setId_jornada(rs.getInt("ID_JORNADA"));
                jor.setNom_jornada(rs.getString("NOM_JORNADA"));
                jornadas.add(jor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());    
        } finally{
            c.cerrarConexion(conn);
        }
        return jornadas;
    }

    public Alumno listarId(int idAlumno) {
        Alumno alumno = new Alumno();
        ResultSet rs = null;
        Connection conn = c.Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from alumno where id_alumno = ?");
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();
            while (rs.next()) {
                alumno.setID_ALUMNO(rs.getInt("ID_ALUMNO"));
                alumno.setNOMBRE_ALUMNO(rs.getString("NOMBRE_ALUMNO"));
                alumno.setRUT_ALUMNO(rs.getString("RUT_ALUMNO"));
                alumno.setCARRERA_ALUMNO(rs.getString("CARRERA_ALUMNO"));
                alumno.setID_JORNADA(rs.getInt("ID_JORNADA"));
                alumno.setCORREO_ALUMNO(rs.getString("CORREO_ALUMNO"));
                alumno.setTELEFONO(rs.getString("TELEFONO"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally{
            c.cerrarConexion(conn);
        }   
        return alumno;
    }
}
