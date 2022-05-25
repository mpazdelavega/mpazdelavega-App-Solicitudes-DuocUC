/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Modelo.FlujoEstado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Silvana
 */
public class DAOFlujoEstado {
        private Connection conn = new Conexion().Conectar();

    
    public FlujoEstado traerFlujoEstrado(int idestado, int idflujo){
        FlujoEstado f = new FlujoEstado();
        try {
            String sql = "select id_responsable from flujo_estado where id_estado = ? and id_flujo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idestado);
            ps.setInt(2, idflujo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
               f.setIdResponsable(rs.getInt("id_responsable"));
               f.setIdEstdado(idestado);
               f.setIdFlujo(idflujo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return f;
    }
    
     public ArrayList<FlujoEstado> traerFlujoEstrados(int idflujo) {
        ArrayList<FlujoEstado> flujos = new ArrayList<>();
        try {
            String sql = "select * from flujo_estado WHERE ID_FLUJO = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idflujo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlujoEstado f = new FlujoEstado();
                f.setIdEstdado(rs.getInt("id_estado"));
                f.setIdFlujo(rs.getInt("id_flujo"));
                flujos.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return flujos;
    }
}
