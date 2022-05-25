/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TipoDocumento;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Silvana
 */
public class DAOTipoDocumento implements CRUD<TipoDocumento> {

    private Connection conn = new Conexion.Conexion().Conectar();
    private String sql_selectAll = "select * from tipo_documento";
    private String sql_insert = "INSERT INTO tipo_documento(COD_TIPO_DOCUMENTO,TIPO_DOCUMENTO) VALUES (?,?)";
    private String sql_delete = "DELETE FROM tipo_documento WHERE COD_TIPO_DOCUMENTO = ?";
    private String sql_selectUsuario = "select * from usuario where login = ? and password = ?";
    private String sql_update = "UPDATE tipo_documento SET TIPO_DOCUMENTO = ? WHERE COD_TIPO_DOCUMENTO = ?";

    @Override
    public boolean crear(TipoDocumento o) {
        try {
            PreparedStatement ps = conn.prepareStatement(sql_insert);
            ps.setInt(1, o.getCodTipoDocto());
            ps.setString(2, o.getTipoDocto());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int o) {
        boolean retorno = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql_delete);
            ps.setInt(1, o);
            if (ps.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    @Override
    public boolean modificar(TipoDocumento o) {
        boolean retorno = false;
        try {
            PreparedStatement ps = conn.prepareStatement(sql_update);
            ps.setString(1, o.getTipoDocto());
            ps.setInt(2, o.getCodTipoDocto());
            if (ps.executeUpdate() > 0) {
                retorno = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return retorno;
    }

    @Override
    public ArrayList<TipoDocumento> listarTodo() {
        ArrayList<TipoDocumento> tipos = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TipoDocumento tp = new TipoDocumento();
                tp.setCodTipoDocto(rs.getInt("COD_TIPO_DOCUMENTO"));
                tp.setTipoDocto(rs.getString("TIPO_DOCUMENTO"));
                tipos.add(tp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tipos;

    }

    @Override
    public boolean eliminar(TipoDocumento o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
