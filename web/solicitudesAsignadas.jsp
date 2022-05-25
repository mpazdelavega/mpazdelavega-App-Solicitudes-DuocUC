<%-- 
    Document   : MisSolicitudes
    Created on : 16-06-2019, 17:49:53
    Author     : Silvana
--%>


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
    
    if (session.getAttribute("nombreusuario") == null) {
        response.sendRedirect("login.jsp");
    }else{
        name = (String) session.getAttribute("nombreusuario");   
        sol = dao.consultarSolicitudes((String)session.getAttribute("usuario"));
        cant = Integer.parseInt(session.getAttribute("cantNotif").toString());
    }
%>
<%-- 
    Document   : home
    Created on : 09-06-2019, 11:34:39
    Author     : Silvana
--%>

<%@page import="Modelo.Usuarios"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Gestión Documental</title>        <!-- Tell the browser to be responsive to screen width -->
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.7 -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- Ionicons -->
        <link href="https://unpkg.com/ionicons@4.5.5/dist/css/ionicons.min.css" rel="stylesheet">
        <!-- Theme style -->
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/css/AdminLTE.min.css">
        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
                      page. However, you can choose any other skin. Make sure you
                      apply the skin class to the body tag so the changes take effect. -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/css/skins/skin-blue.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
            <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet"
              href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
    </head>
    <!--
        BODY TAG OPTIONS:
        =================
        Apply one or more of the following classes to get the
        desired effect
        |---------------------------------------------------------|
        | SKINS         | skin-blue                               |
        |               | skin-black                              |
        |               | skin-purple                             |
        |               | skin-yellow                             |
        |               | skin-red                                |
        |               | skin-green                              |
        |---------------------------------------------------------|
        |LAYOUT OPTIONS | fixed                                   |
        |               | layout-boxed                            |
        |               | layout-top-nav                          |
        |               | sidebar-collapse                        |
        |               | sidebar-mini                            |
        |---------------------------------------------------------|
    -->

    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <jsp:include page="Menu_SUP.jsp">                      
                <jsp:param name="cantNotif" value="${cant}"/>
            </jsp:include>
                        
            <!-- INCORPORACION DE LATERAL IZQUIERDO -->          
            <jsp:include page="Menu_IZQ.jsp"/>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Mis Asignadas
                        <small>Aqui encontrara todas las solicitudeds que le han asignado</small>
                    </h1>

                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Inbox</h3>

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
                                        for (Solicitud s : sol) {

                                            DAO.DAOUsuario da = new DAOUsuario();
                                            DAO.DAOSolicitud ds = new DAOSolicitud();

                                            Usuarios userSend = da.traerUsuario(s.getIdUsuarioCreador());
                                            Usuarios userSendResp = da.traerUsuarioResponsable(s.getIdUsuarioResponsable());
                                            String cadena = s.getDescripcion().length() > 20 ? s.getDescripcion().substring(0, 20) : s.getDescripcion().substring(0, s.getDescripcion().length());
                                            out.print("<tr id='row" + (cont++) + "' value='" + s.getIdSolicitud() + "' style='cursor: pointer;' >"
                                                    + "<td name='idSol' class='mailbox-number'>" + s.getIdSolicitud() + "</td>"
                                                    + "<td class='mailbox-name'><a href=Solicitud.jsp?tiposol=asignadas&idsolicitud=" + s.getIdSolicitud() + " >" + userSend.getNombre() + "</a></td>"
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
            <!-- /.content-wrapper -->  
            <!-- Control Sidebar -->
            <aside class="control-sidebar control-sidebar-dark">
                <!-- Create the tabs -->
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                    <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
                    <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <!-- Home tab content -->
                    <div class="tab-pane active" id="control-sidebar-home-tab">
                        <h3 class="control-sidebar-heading">Recent Activity</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:;">
                                    <i class="menu-icon fa fa-birthday-cake bg-red"></i>

                                    <div class="menu-info">
                                        <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

                                        <p>Will be 23 on April 24th</p>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                        <h3 class="control-sidebar-heading">Tasks Progress</h3>
                        <ul class="control-sidebar-menu">
                            <li>
                                <a href="javascript:;">
                                    <h4 class="control-sidebar-subheading">
                                        Custom Template Design
                                        <span class="pull-right-container">
                                            <span class="label label-danger pull-right">70%</span>
                                        </span>
                                    </h4>

                                    <div class="progress progress-xxs">
                                        <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <!-- /.control-sidebar-menu -->

                    </div>
                    <!-- /.tab-pane -->
                    <!-- Stats tab content -->
                    <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
                    <!-- /.tab-pane -->
                    <!-- Settings tab content -->
                    <div class="tab-pane" id="control-sidebar-settings-tab">
                        <form method="post">
                            <h3 class="control-sidebar-heading">General Settings</h3>

                            <div class="form-group">
                                <label class="control-sidebar-subheading">
                                    Report panel usage
                                    <input type="checkbox" class="pull-right" checked>
                                </label>

                                <p>
                                    Some information about this general settings option
                                </p>
                            </div>
                            <!-- /.form-group -->
                        </form>
                    </div>
                    <!-- /.tab-pane -->
                </div>
            </aside>
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                    immediately after the control sidebar -->
            <div class="control-sidebar-bg"></div>
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