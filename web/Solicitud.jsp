<%-- 
    Document   : newsol
    Created on : 16-06-2019, 18:18:22
    Author     : Silvana
--%>
<%@page import="Modelo.Motivos"%>
<%@page import="DAO.DAOMotivos"%>
<%@page import="Modelo.Alumno"%>
<%@page import="Modelo.Carreras"%>
<%@page import="Modelo.Jornada"%>
<%@page import="DAO.DAOAlumno"%>
<%@page import="Modelo.FlujoEstado"%>
<%@page import="DAO.DAOFlujoEstado"%>
<%@page import="Modelo.Archivos"%>
<%@page import="DAO.DAOArchivos"%>
<%@page import="DAO.DAOUsuario"%>
<%@page import="Modelo.Flujo"%>
<%@page import="Modelo.Solicitud"%>
<%@page import="DAO.DAOTipoDocumento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DAOSolicitud"%>
<%@page import="Modelo.TipoDocumento"%>

<%
    String name = "";
    String tiposol = "";
    int idUsuario = 0;
    DAO.DAOSolicitud daosol = new DAOSolicitud();
    Solicitud sol = new Solicitud();
    int cant = 0;
    
    
    if (session.getAttribute("nombreusuario") == null) {
        response.sendRedirect("login.jsp");
    }else{
        if(request.getParameter("idsolicitud") != null){
            name = (String) session.getAttribute("nombreusuario");
            idUsuario = Integer.parseInt(session.getAttribute("usuario").toString());
            int idSolicitud = Integer.valueOf(request.getParameter("idsolicitud").trim());
            sol = daosol.traerSolicitud(idSolicitud);
            tiposol = request.getParameter("tiposol");
            cant = Integer.parseInt(session.getAttribute("cantNotif").toString());  
        }else{
            response.sendRedirect("home.jsp");
        }
    }
%>
<%-- 
    Document   : MisSolicitudes
    Created on : 16-06-2019, 17:49:53
    Author     : Silvana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
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
                        Mis Solicitudes
                        <small>Aqui encontrara toda las solicitudes que ha creado</small>
                    </h1>

                </section>

                <!-- Main content -->
                <section class="content container-fluid">
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">General Elements</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <%
                                DAOFlujoEstado fe = new DAOFlujoEstado();
                                ArrayList<FlujoEstado> listaFe = fe.traerFlujoEstrados(sol.getIdflujo());
                                int idEstado = sol.getCodEstado();
                                int update = 0;
                                int penultimo = 0;
                                for (FlujoEstado fl : listaFe){
                                    if (fl.getIdEstdado()== idEstado) {
                                        update = listaFe.get(listaFe.indexOf(fl) + 1).getIdEstdado();
                                        penultimo = listaFe.get(listaFe.size() - 2).getIdEstdado();
                                    }
                                }
                                String action = "";
                                
                                if (update == 5) {
                                    action = "Cerrar";
                                } else {
                                    action = "Guardar";
                                }
                            %>
                            <form role="form" method="post" action="<% out.print(action); %>">
                                <input type="hidden" name="idsolicitud" id="idsol"  value="<% out.print(sol.getIdSolicitud());%>"/>
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Nombre Solicitud</label>
                                    <input type="text" value="<%out.write(sol.getNombreSolicitud());%>" class="form-control" name="nombresolicitud" placeholder="Nombre Solicitud..." style="width: 80%" disabled/>
                                </div>
                                <!-- textarea -->

                                <div class="form-group" style="width: 80%">
                                    <label>Descripción</label>
                                    <textarea class="form-control" rows="5" name="descripcion" id="descripcion" placeholder="Descripción de la solicitud..." disabled><%out.write(sol.getDescripcion());                                  %></textarea>
                                </div>
                                <div class="form-group" style="width: 80%">
                                    <label>Flujos</label>
                                    <select class="form-control" disabled>
                                        <% DAOSolicitud dao = new DAOSolicitud();
                                            ArrayList<Flujo> listaFlujos = dao.traerFlujos();

                                            for (Flujo cadena : listaFlujos) {
                                                if (sol.getIdflujo() == cadena.getIdFlujo()) {
                                                    out.print("<option  value=" + cadena.getIdFlujo() + " selected='selected'>" + cadena.getNombreFlujo() + "</option>");
                                                } else {
                                                    out.print("<option value=" + cadena.getIdFlujo() + ">" + cadena.getNombreFlujo() + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                        <input type="hidden" value="<%out.print(sol.getIdflujo());%>" id="tipoflujo" name="tipoflujos"/>
                                </div>
                                
                                <!-- Se despliega un combobox con el usuario creador seleccionado al momento de crear la solicitud.*/-->
                                <div class="form-group" style="width: 80%">
                                    <label>Usuario Creador</label>
                                    <div id="collapseExample">
                                        <div class="card card-body">
                                            <%  
                                                DAOUsuario daousu = new DAOUsuario();
                                                    out.print("<select class='form-control'  id='selectCrea' disabled >");

                                                ArrayList<Usuarios> listaUsuariosCreadores = daousu.listarTodo();
                                                for (Usuarios usu : listaUsuariosCreadores) {

                                                    if (usu.getIdPerfil() == 1) {
                                                        if (usu.getIdUsuario() == sol.getIdUsuarioCreador()) {
                                                            out.print("<option selected value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>");
                                                        } else {
                                                            out.print("<option value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>");
                                                        }
                                                    }
                                                }
                                                out.print("</select>");
                                            %>
                                            <input type='hidden' id="creador" name='idusuarioCreador'/>
                                        </div>
                                    </div>
                                        <%
                                            DAOAlumno al = new DAOAlumno();
                                            Alumno alum = al.listarId(sol.getIdAlumno());
                                        %>
                                </div>
                                
                                <!-- Se despliega un combobox con el usuario responsable seleccionado al momento de crear la solicitud.*/-->
                                <div class="form-group" style="width: 80%">
                                    <label>Seleccionar Responsable</label>
                                    <div id="collapseExample">
                                        <div class="card card-body">
                                            
                                            <%  
                                                if (update == 5) {
                                                    out.print("<select class='form-control'  id='selectResp' disabled >");
                                                }
                                                else{
                                                    out.print("<select class='form-control'  id='selectResp' >");
                                                }
                                            %>
                                            <%
                                                ArrayList<Usuarios> listaUsuariosResponsables = daousu.listarTodo();
                                                for (Usuarios usu : listaUsuariosResponsables) {
                                                    
                                                    if (usu.getIdPerfil() == 2) {
                                                        if (usu.getIdUsuario() == sol.getIdUsuarioResponsable()) {
                                                            out.print("<option selected value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>");
                                                        } else {
                                                            out.print("<option value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>");
                                                        }
                                                    }
                                                }
                                                out.print("</select>");
                                            %>
                                            <input type='hidden' id="responsable" name='idusuarioResponsable'/>
                                        </div>
                                    </div>
                                </div>
                                    
                                <!-- Se despliegan el resto de campos del formulario.*/-->
                                <div class="form-group" style="width: 80%">
                                    <label>Nombre Alumno</label>
                                    <input type="text" class="form-control" name="nombrealumno" id="nombrealumno" placeholder="Nombre Alumno..." value="<%out.print(alum.getNOMBRE_ALUMNO());%>" disabled/>
                                </div>

                                <div class="form-group" style="width: 80%">
                                    <label>Rut Alumno</label>
                                    <input type="text" class="form-control" name="rutalumno" id="rut" placeholder="Rut Alumno..." value="<%out.print(alum.getRUT_ALUMNO());%>"  disabled/>
                                </div>


                                <div class="form-group" style="width: 80%">
                                    <label>Carrera</label>
                                    <input type="text" class="form-control" name="carrera" id="carrera" placeholder="Rut Alumno..." value="<%out.print(alum.getCARRERA_ALUMNO());%>"  disabled/>
                                </div>
                                <div class="form-group" style="width: 80%">
                                    <label>Jornada</label>
                                    <select class="form-control" name="idjornada" id="idjornada" disabled >
                                        <% DAOAlumno daoal = new DAOAlumno();
                                            ArrayList<Jornada> listaJornada = daoal.listarTodo();
                                            for (Jornada jor : listaJornada) {
                                                if (alum.getID_JORNADA() == jor.getId_jornada()) {
                                                    out.print("<option value=" + jor.getId_jornada() + " selected>" + jor.getNom_jornada() + "</option>");
                                                } else {
                                                    out.print("<option value=" + jor.getId_jornada() + ">" + jor.getNom_jornada() + "</option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                    
                                    <input type='hidden' id="jornada" name='jornada'/>

                                <div class="form-group" style="width: 80%">
                                    <label>Correo Alumno</label>
                                    <input type="email" class="form-control" name="correoalumno" id="correoalumno" placeholder="Correo..."  value="<% out.print(alum.getCORREO_ALUMNO());%> " disabled/>
                                </div>    
                                <div class="form-group" style="width: 80%">
                                    <label>Telefono Alumno</label>
                                    <input type="tel" class="form-control" id="telefono" name="telefono" placeholder="912345678 " maxlength="9" value="<%out.print(alum.getTELEFONO());%>" disabled/>
                                </div>
                                
                                <div class="form-group" style="width: 80%">
                                <label>Añada alguna observacion que considere importante</label>
                                <textarea class="form-control" rows="5" id="observacion" name="observacion" placeholder="Añadir comentarios..."></textarea>
                                 
                                </div>
                                <div class="form-group" style="width: 80%">
                                    <label>Número de Ticket (Solo si va a cerrar la solicitud)</label>
                                    <input type="text" class="form-control" id="ticket" name="ticket" placeholder="Numero de ticket..." />
                                </div>
                                
                                 <div class="form-group" style="width: 80%">
                                    <label>Seleccione un motivo de rechazo (Solo si va a rechazar la solicitud)</label>
                                    <select class="form-control" name="motivos" id="motivo"  >
                                        <% DAOMotivos mo = new DAOMotivos();
                                            ArrayList<Motivos> listaMotivos = mo.traerMotivos();
                                            for (Motivos m : listaMotivos) {
                                                out.print("<option value=" + m.getIdMotivo() + ">" + m.getMotivo() + "</option>");
                           
                                            }
                                        %>
                                    </select>
                                   
                                </div>
                                 
                                <%
                                    DAOArchivos daoarch = new DAOArchivos();
                                    Archivos arch = new Archivos();
                                    arch = daoarch.obtenerArchivo(sol.getIdSolicitud());
                                    if (arch.getIdArchivo() > 0) {
                                        out.write("<div class='box-footer'>"
                                                + "<ul class='mailbox-attachments clearfix'>"
                                                + "<li>"
                                                + "<span class='mailbox-attachment-icon'><i class='fa fa-file-pdf-o'></i></span>"
                                                + "<div class='mailbox-attachment-info'>"
                                                + "<a href=" + arch.getRuta() + " class='mailbox-attachment-name' target='new'><i class='fa fa-paperclip'></i>" + arch.getNombreArchivo() + "</a>"
                                                + "<span class='mailbox-attachment-size'>"
                                                + "<a href=" + arch.getRuta() + " download=" + arch.getNombreArchivo() + " class='btn btn-default btn-xs pull-right'><i class='fa fa-cloud-download'></i></a>"
                                                + "</span>"
                                                + "</div>"
                                                + "</li>"
                                                + "</ul>"
                                                + "</div>");
                                    }
                                %>


                                <div class="box-footer">
                                    <%
                                        //quitar la Variable id usuario y subirla para poder trabajarla en todas las partes del jsp
                                        if (update != 5 && idUsuario == sol.getIdUsuarioResponsable()) {
                                            out.print("<button style='margin-left: 1vw;' type='submit' class='btn btn-primary' id='aprobar' >Aprobar</button>");
                                            out.print("<input type='button' style='margin-left: 1vw' class='btn btn-primary' id='rechazar' value='Rechazar' />");
                                        } else if (update == 5 && idUsuario == sol.getIdUsuarioResponsable()) {
                                            out.print(" <button type='submit' class='btn btn-primary'>Cerrar</button>");

                                        } else {

                                        }

                                    %>

                                </div>

                            </form>
                        </div>
                        <!-- /.box-body -->
                    </div>

            </div>
            <!--------------------------
            | Your Page Content Here |
            -------------------------->


            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <!-- Main Footer -->


        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Create the tabs -->
            <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
                <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
                </li>
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

        <!-- ./wrapper -->

        <!-- REQUIRED JS SCRIPTS -->

        <!-- jQuery 3 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/js/adminlte.min.js"></script>
        <script>
            
            $(document).ready(function(){
               $('#rechazar').click(function(event){
                   var id = $('#idsol').val();
                   var ido = $('#observacion').val();
                   var idm = $('#motivo').val();
                   var idt = $('#tipoflujo').val();
                   var nombal = $('#nombrealumno').val();
                   var rut = $('#rut').val();
                   var idjor = $('#idjornada').val();
                   var carrera = $('#carrera').val();
                   var telefono = $('#telefono').val();
                   var descripcion = $('#descripcion').val();
                   var correo = $('#correoalumno').val();
                   var idUsuarioCrea = $('#creador').val();
                   var idUsuarioResp = $('#responsable').val();
                   
                   if(confirm('Esta apunto de rechazar una solicitud, ¿esta usted seguro ?')){
                   $.post('Rechazar',{
                   idsol : id, idusu: <%out.print(idUsuario);%>, motivo : idm, observacion : ido, 
                   tipoflujo: idt, nombrealumno: nombal, rut: rut, idjornada: idjor, carrera: carrera,
                   telefono: telefono, descripcion: descripcion, correoalumno: correo, creador: idUsuarioCrea, 
                   responsable: idUsuarioResp
                       },
                        function () {
                        alert("se ha rechazado correctamente la solicitud");
                        window.location.href = "./home.jsp";

                    });
                    }
               });
               
            });
            
            $(document).ready(function(){
               $('#aprobar').click(function(event){
                   var id = $('#idsol').val();
                   var ido = $('#observacion').val(); 
                   var idm = $('#motivo').val();
                   var idt = $('#tipoflujo').val();
                   var nombal = $('#nombrealumno').val();
                   var rut = $('#rut').val();
                   var idjor = $('#idjornada').val();
                   var carrera = $('#carrera').val();
                   var telefono = $('#telefono').val();
                   var descripcion = $('#descripcion').val();
                   var correo = $('#correoalumno').val();
                   var idUsuarioCrea = $('#creador').val();
                   var idUsuarioResp = $('#responsable').val();
                   
                   
                   
                   $.post('EmailAprobar',{
                   idsol : id, motivo : idm, observacion : ido,
                   tipoflujo: idt, nombrealumno: nombal, rut: rut, idjornada: idjor, carrera: carrera,
                   telefono: telefono, descripcion: descripcion, correoalumno: correo, creador: idUsuarioCrea, 
                   responsable: idUsuarioResp
                       },
                        function () {
                        alert("Solicitud aprobada con exito");
                        window.location.href = "./home.jsp";

                    });
                   
               });
            });
            
             
            
             $(document).ready(function (){
                $('#responsable').val($('#selectResp').val())
                $('#creador').val($('#selectCrea').val())
            })
            $('#selectResp').change(function(evt){
                $('#responsable').val(evt.currentTarget.value);
            })
            $('#selectCrea').change(function(evt){
                $('#creador').val(evt.currentTarget.value);
            })
            
             $(document).ready(function (){
                $('#jornada').val($('#idjornada').val())
            })
            $('#idjornada').change(function(evt){
                $('#jornada').val(evt.currentTarget.value);
            })
            
        </script>
        
        
                       <!-- Optionally, you can add Slimscroll and FastClick plugins.
                     Both of these plugins are recommended to enhance the
                     user experience. -->
    </body>

</html>
