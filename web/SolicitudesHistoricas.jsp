<%-- 
    Document   : MisSolicitudes
    Created on : 16-06-2019, 17:49:53
    Author     : Silvana
--%>


<%@page import="DAO.DAOAlumno"%>
<%@page import="DAO.DAOSolicitud"%>
<%@page import="DAO.DAOUsuario"%>
<%@page import="Modelo.Solicitud"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name = "";
    ArrayList<Solicitud> sol = new ArrayList();
    DAOSolicitud dao = new DAOSolicitud();
    int cant = 0;
    name = String.valueOf(session.getAttribute("nombreusuario"));
        if(name == null || name == "null" || name == "" ){
            System.out.println("-------------------" + name);
            response.sendRedirect("login.jsp");
        }else{
            name = (String) session.getAttribute("nombreusuario");
            cant = Integer.parseInt(session.getAttribute("cantNotif").toString());  
            sol = dao.listarTodo();
        }
%>
<%-- 
    Document   : home
    Created on : 09-06-2019, 11:34:39
    Author     : Silvana
--%>

<%@page import="Modelo.Usuarios"%>
<%@page import="Modelo.Alumno"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Gestión Documental</title>        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://unpkg.com/ionicons@4.5.5/dist/css/ionicons.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/css/AdminLTE.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/css/skins/skin-blue.css">
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">
            <jsp:include page="Menu_SUP.jsp">                      
                <jsp:param name="cantNotif" value="${cant}"/>
            </jsp:include>
            <!-- Left side column. contains the logo and sidebar -->
            
            <!-- INCORPORACION DE LATERAL IZQUIERDO -->          
            <jsp:include page="Menu_IZQ.jsp"/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Solicitudes Historicas
                        <small>Aqui encontrara toda las solicitudes que ya se encuentran cerradas</small>
                    </h1>

                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Caja de solicitudes</h3>

                            <div class="box-tools pull-right">
                                <div class="has-feedback">
                                    <input type="text" class="form-control input-sm" placeholder="Search Mail">
                                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                </div>
                            </div>
                            <!-- /.box-tools -->
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body no-padding">
                            <div class="table-responsive mailbox-messages">
                                <table id="table" class="table table-hover table-striped">
                                    <%
                                        int cont = 0;
                                        DAO.DAOUsuario da = new DAOUsuario();
                                        DAO.DAOSolicitud ds = new DAOSolicitud();
                                        DAO.DAOAlumno al = new DAOAlumno();
                                        for (Solicitud s : sol) {

                                            
                                            Alumno alumSend = al.listarId(s.getIdAlumno());
                                            
                                            Usuarios userSend = da.traerUsuario(s.getIdUsuarioCreador());
                                            Usuarios userSendResp = da.traerUsuarioResponsable(s.getIdUsuarioResponsable());
                                            String cadena = s.getDescripcion().length() > 20 ? s.getDescripcion().substring(0, 20) : s.getDescripcion().substring(0, s.getDescripcion().length());
                                            //out.print(s.getIdAlumno());
                                            out.print("<tr id='row" + (cont++) + "' value='" + s.getIdSolicitud() + "' style='cursor: pointer;' >"
                                                    + "<td name='idSol' class='mailbox-number'>" + s.getIdSolicitud() + "</td>"
                                                    + "<td class='mailbox-name'><a href=Solicitud.jsp?tiposol=asignadas&idsolicitud=" + s.getIdSolicitud() + " >" + userSend.getNombre() + "</a></td>"
                                                    + "<td class='mailbox-name'>" + alumSend.getNOMBRE_ALUMNO() + "</td>"
                                                    + "<td class='mailbox-name'>" + alumSend.getRUT_ALUMNO() + "</td>"
                                                    + "<td class='mailbox-name'>" + alumSend.getCARRERA_ALUMNO() + "</td>"
                                                    + "<td class='mailbox-name'>" + userSendResp.getNombre() + "</td>"
                                                    + "<td class='mailbox-subject'><b>" + cadena + "...</b></td>"
                                                    + "<td class='mailbox-attachment'><i class='fa fa-paperclip'></i></td>"
                                                    + "<td class='mailbox-date'> " + ds.joinEstado(s.getCodEstado()) + "</td></tr>");
                                        }
                                    %>


                                </table>
                                <!-- /.table -->
                            </div>
                            <!-- /.mail-box-messages -->
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.pull-right -->



                    <!--------------------------
                    | Your Page Content Here |
                    -------------------------->

                </section>
                <!-- /.content -->
            </div>
        </div>
        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/js/adminlte.min.js"></script>
    </body>

</html>