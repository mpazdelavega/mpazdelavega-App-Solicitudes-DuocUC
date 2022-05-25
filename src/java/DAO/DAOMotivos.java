/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Modelo.Motivos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Felipe
 */
public class DAOMotivos {
    
   
     Conexion c = new Conexion();

     public ArrayList<Motivos> traerMotivos() {
        ArrayList<Motivos> motivos = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from motivos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Motivos motivo = new Motivos();
                motivo.setIdMotivo(rs.getInt("IdMotivo"));
                motivo.setMotivo(rs.getString("motivo"));
                motivos.add(motivo);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        
        return motivos;
        
    }
    
    public boolean pasarMotivos(int idmotivo, int idsoli) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET id_motivo = ? WHERE id_solicitud = ?");
            ps.setInt(1, idmotivo);
            ps.setInt(2, idsoli);
            int update = ps.executeUpdate();
            if (update > 0) {
                retorno = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }

        return retorno;
    }
}
