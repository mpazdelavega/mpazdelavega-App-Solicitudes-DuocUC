/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Modelo.Carreras;
import Modelo.Estado;
import Modelo.Flujo;
import Modelo.Solicitud;
import Modelo.Usuarios;
import interfaces.CRUD;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.FlujoEstado;

/**
 *
 * @author Silvana
 */
public class DAOSolicitud implements CRUD<Solicitud> {
    Conexion c = new Conexion();
    //private Connection conn = new Conexion().Conectar();
    private String sql_selectAll = "select * from solicitud";
    private String sql_insert = "INSERT INTO solicitud VALUES (default,?,?,sysdate(),?,?,?,?,?,?,?,?,?)";
    private String sql_delete = "DELETE FROM solicitud WHERE id_solicitud = ?";
    private String sql_selectUsuario = "select * from solicitud where id_usuario_responsable = ?";
    private String sql_notificaciones = "select ID_SOLICITUD,nombre_solicitud from solicitud where ID_USUARIO_RESPONSABLE = ? and COD_ESTADO != 5";
    private String sql_selectUsuarioCreador = "select * from solicitud where id_usuario_crea = ?";
    private String sql_update = "UPDATE tipo_documento SET TIPO_DOCUMENTO = ? WHERE COD_TIPO_DOCUMENTO = ?";

    @Override
    public boolean crear(Solicitud o) {
        boolean respuesta = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getDescripcion());
            ps.setString(2, o.getNombreSolicitud());
            ps.setInt(3, o.getIdUsuarioCreador());
            ps.setInt(4, o.getIdUsuarioResponsable());
            ps.setInt(5, o.getCodTipoDocumento());
            ps.setString(6, o.getOtro());
            ps.setInt(7, o.getCodEstado());
            ps.setInt(8, o.getIdflujo());
            ps.setInt(9, o.getIdMotivo());
            ps.setString(10, o.getObservacion());
            ps.execute();
            respuesta = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return respuesta;
    }

    public int crearConid(Solicitud o) {
        int id = 1;
        Connection conn = new Conexion().Conectar();
        try {
            //Para la creación del insert en esta función, se deben indicar los nombres de las columnas en la que estará cada atributo,
            //para que la sentencia reconozca donde insertar.
            PreparedStatement ps = conn.prepareStatement("INSERT INTO solicitud "
                    + "(descripcion, nombre_solicitud, fecha_solicitud, id_usuario_crea, id_usuario_responsable, id_alumno, cod_tipo_documento, otro, cod_estado, id_flujo, id_motivo, observaciones) "
                    + "VALUES (?,?,sysdate(),?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getDescripcion());
            ps.setString(2, o.getNombreSolicitud());
            ps.setInt(3, o.getIdUsuarioCreador());
            ps.setInt(4, o.getIdUsuarioResponsable());
            ps.setInt(5, o.getIdAlumno());
            ps.setInt(6, o.getCodTipoDocumento());
            ps.setString(7, o.getOtro());
            ps.setInt(8, o.getCodEstado());
            ps.setInt(9, o.getIdflujo());
            ps.setInt(10, o.getIdMotivo());
            ps.setString(11, o.getObservacion());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return id;
    }

    /**
     * este metodo trae los flujos agregados en la base de datos, para hacer uso
     * de ellos en el front-end.
     *
     * @return An ArrayList String.
     */
    public ArrayList<Flujo> traerFlujos() {
        ArrayList<Flujo> flujos = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from flujo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Flujo flujo = new Flujo();
                flujo.setIdFlujo(rs.getInt("id_flujo"));
                flujo.setNombreFlujo(rs.getString("nombre"));
                flujos.add(flujo);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return flujos;
    }

    public String joinEstado(int codEstado) {
        String nombre = null;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("Select estado from estado where cod_estado = ?;");
            ps.setInt(1, codEstado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nombre = rs.getString("estado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return nombre;
    }

    /**
     * Metodo que consulta las solicitudes dentro de la base de datos
     * dependiendo del usuario que haga la consulta
     *
     * @param o Usuarios.
     * @return un ArrayList de Solicitud.
     */
    //   u = id_usuario_responsable
    public ArrayList<Solicitud> consultarSolicitudes(String u) {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuario);
            
            ps.setString(1, u);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(rs.getInt("id_solicitud"));
                s.setDescripcion(rs.getString("DESCRIPCION"));
                s.setCodEstado(rs.getInt("COD_ESTADO"));
                s.setCodTipoDocumento(rs.getInt("COD_TIPO_DOCUMENTO"));
                s.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
                s.setIdUsuarioCreador(rs.getInt("ID_USUARIO_CREA"));
                s.setIdUsuarioResponsable(rs.getInt("ID_USUARIO_RESPONSABLE"));
                s.setIdAlumno(rs.getInt("ID_ALUMNO"));
                s.setOtro(rs.getString("otro"));
                s.setIdflujo(rs.getInt("ID_FLUJO"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
                solicitudes.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return solicitudes;
    }
    
    public ArrayList<Solicitud> consultarNotificaciones(String u) {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_notificaciones);
            
            ps.setString(1, u);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(rs.getInt("id_solicitud"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
                solicitudes.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return solicitudes;
    }
    /*
    public ArrayList<Solicitud> consultarSolicitudes(int id) {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuario);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(rs.getInt("id_solicitud"));
                s.setDescripcion(rs.getString("DESCRIPCION"));
                s.setCodEstado(rs.getInt("COD_ESTADO"));
                s.setCodTipoDocumento(rs.getInt("COD_TIPO_DOCUMENTO"));
                s.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
                s.setIdUsuarioCreador(rs.getInt("ID_USUARIO_CREA"));
                s.setIdUsuarioResponsable(rs.getInt("ID_USUARIO_RESPONSABLE"));
                s.setIdAlumno(rs.getInt("ID_ALUMNO"));
                s.setOtro(rs.getString("otro"));
                s.setIdflujo(rs.getInt("ID_FLUJO"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
                solicitudes.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return solicitudes;
    }
    */
    public ArrayList<Solicitud> consultarSolicitudesUsuCrea(String id) {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectUsuarioCreador);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(rs.getInt("id_solicitud"));

                s.setDescripcion(rs.getString("DESCRIPCION"));
                s.setCodEstado(rs.getInt("COD_ESTADO"));
                s.setCodTipoDocumento(rs.getInt("COD_TIPO_DOCUMENTO"));
                s.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
                s.setIdUsuarioCreador(rs.getInt("ID_USUARIO_CREA"));
                s.setIdUsuarioResponsable(rs.getInt("ID_USUARIO_RESPONSABLE"));
                s.setIdAlumno(rs.getInt("ID_ALUMNO"));
                s.setOtro(rs.getString("otro"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
                solicitudes.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return solicitudes;
    }

    public Solicitud traerSolicitud(int idsol) {
        Solicitud s = new Solicitud();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("Select * from solicitud where id_solicitud = ?");
            ps.setInt(1, idsol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                s.setIdSolicitud(rs.getInt("id_solicitud"));
                s.setDescripcion(rs.getString("DESCRIPCION"));
                s.setCodEstado(rs.getInt("COD_ESTADO"));
                s.setCodTipoDocumento(rs.getInt("COD_TIPO_DOCUMENTO"));
                s.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
                s.setIdUsuarioCreador(rs.getInt("ID_USUARIO_CREA"));
                s.setIdUsuarioResponsable(rs.getInt("ID_USUARIO_RESPONSABLE"));
                s.setIdAlumno(rs.getInt("ID_ALUMNO"));
                s.setIdflujo(rs.getInt("id_flujo"));
                s.setOtro(rs.getString("otro"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return s;
    }

    public int consultarEstado(int idSol) {
        int idEstado = 0;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COD_ESTADO FROM solicitud WHERE ID_SOLICITUD = ?");
            ps.setInt(1, idSol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idEstado = rs.getInt("COD_ESTADO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return idEstado;
    }
    
    public String consultarNombreEstado(int codEstado) {
        String estado = "";
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT ESTADO FROM estado WHERE COD_ESTADO = ?");
            ps.setInt(1, codEstado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                estado = rs.getString("ESTADO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return estado;
    }

    public boolean actualizarSoli(int idResponsable, int idEst, String observacion, int idSoli) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET ID_USUARIO_RESPONSABLE = ?, COD_ESTADO = ?, "
                    + "observaciones = ? "
                    + "WHERE id_solicitud = ?");
            ps.setInt(1, idResponsable);
            ps.setInt(2, idEst);
            ps.setString(3, observacion);
            ps.setInt(4, idSoli);
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
    

   
    
    public boolean pasarUsuarioCreador(int idSoli,int idCreador) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET ID_USUARIO_CREA = ? WHERE id_solicitud = ?");
            ps.setInt(1, idCreador);
            ps.setInt(2, idSoli);
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
    
    public boolean pasarUsuarioResponsable(int idSoli,int idResponsable) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET ID_USUARIO_RESPONSABLE = ? WHERE id_solicitud = ?");
            ps.setInt(1, idResponsable);
            ps.setInt(2, idSoli);
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
    
    public boolean pasarObservacion(String cadena,int idSoli) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET observaciones = ? WHERE id_solicitud = ?");
            ps.setString(1, cadena);
            ps.setInt(2, idSoli);
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
    
    

    public boolean cerrarSoli(int idResponsable, int idSoli, int ticket) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET ID_USUARIO_RESPONSABLE = ?, COD_ESTADO = ?, numero_ticket = ? WHERE id_solicitud = ?");
            ps.setInt(1, idResponsable);
            ps.setInt(2, 5);
            ps.setInt(3, ticket);
            ps.setInt(4, idSoli);
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

    @Override
    public boolean eliminar(Solicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(int o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(Solicitud o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Solicitud> listarTodo() {
        ArrayList<Solicitud> solicitudes = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_selectAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setIdSolicitud(rs.getInt("id_solicitud"));
                s.setDescripcion(rs.getString("DESCRIPCION"));
                s.setCodEstado(rs.getInt("COD_ESTADO"));
                s.setCodTipoDocumento(rs.getInt("COD_TIPO_DOCUMENTO"));
                s.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD"));
                s.setIdUsuarioCreador(rs.getInt("ID_USUARIO_CREA"));
                s.setIdUsuarioResponsable(rs.getInt("ID_USUARIO_RESPONSABLE"));
                s.setIdAlumno(rs.getInt("ID_ALUMNO"));
                s.setOtro(rs.getString("otro"));
                s.setNombreSolicitud(rs.getString("nombre_solicitud"));
                solicitudes.add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return solicitudes;
    }

    public int contarSoli() {
        int cont = 0;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("Select count(*)cantidad from solicitud;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cont = rs.getInt("cantidad");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return cont;
    }

    public ArrayList<Carreras> traerCarreras() {
        ArrayList<Carreras> carreras = new ArrayList<>();
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from carreras");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Carreras car = new Carreras();
                car.setIdCarrera(rs.getInt("ID_CARRERA"));
                car.setNombreCarrera(rs.getString("NOMBRE_CARRERA"));
                carreras.add(car);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            c.cerrarConexion(conn);
        }
        return carreras;
    }

    public boolean rechazarSolicitud(int idMotivo, String observacion, int idSoli) {
        boolean retorno = false;
        Connection conn = new Conexion().Conectar();
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE solicitud SET ID_USUARIO_RESPONSABLE = ?, COD_ESTADO = ?, "
                    + "id_motivo = ?, observaciones = ? "
                    + "WHERE id_solicitud = ?");
            ps.setInt(1, -1);
            ps.setInt(2, 6);
            ps.setInt(3, idMotivo);
            ps.setString(4, observacion);
            ps.setInt(5, idSoli);
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
