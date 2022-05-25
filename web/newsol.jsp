<%-- 
    Document   : newsol
    Created on : 16-06-2019, 18:18:22
    Author     : Silvana
--%>
<%@page import="Modelo.Carreras"%>
<%@page import="Modelo.Jornada"%>
<%@page import="Modelo.Alumno"%>
<%@page import="DAO.DAOAlumno"%>
<%@page import="DAO.DAOUsuario"%>
<%@page import="Modelo.Flujo"%>
<%@page import="DAO.DAOTipoDocumento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAO.DAOSolicitud"%>
<%@page import="Modelo.TipoDocumento"%>
<%
    String name = "";
    int cant = 0;
    //HttpSession ses = request.getSession(true);
    if (session.getAttribute("nombreusuario") == null) {
        response.sendRedirect("login.jsp");
    }else{
        name = (String) session.getAttribute("nombreusuario");
        cant = Integer.parseInt(session.getAttribute("cantNotif").toString());
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

    <head lang="es"> 
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Gestión Documental</title>        <!-- Tell the browser to be responsive to screen width -->
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
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
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/css/bootstrapValidator.min.css"/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
                <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
                <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->

        <!-- Google Font -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/css/bootstrap-select.min.css" />
        <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.2/js/bootstrap-select.min.js"></script>
        
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
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <form role="form" method="post" action="NuevaSol" id="formulario" enctype="multipart/form-data">
                                <!-- text input -->
                                <div class="form-group">
                                    <label>Nombre Solicitud</label>
                                    <input type="text" class="form-control " name="nombresolicitud" placeholder="Nombre Solicitud..." />
                                </div>
                                <!-- textarea -->
                                <div class="form-group">
                                    <label>Descripción</label>
                                    <textarea class="form-control" rows="5" name="descripcion" placeholder="Descripción de la solicitud..."></textarea>
                                </div>
                                <div class="form-group">
                                    <label>Flujos</label>
                                    <select class="form-control" name="tipoflujo" id="tipoflujo" onchange="filtro()">
                                        <option value="" disabled selected>Seleccione el Flujo </option>
                                        <% DAOSolicitud dao = new DAOSolicitud();
                                            ArrayList<Flujo> listaFlujos = dao.traerFlujos();
                                            for (Flujo cadena : listaFlujos) {
                                                out.print("<option value=" + cadena.getIdFlujo() + ">" + cadena.getNombreFlujo() + "</option>");
                                            }
                                        %>

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Responsable</label>
                                    <select class="form-control" name="idusuario" id="idusuario">
                                        <option value="" disabled selected>Seleccione un Responsable</option>
                                        <% DAOUsuario daousu = new DAOUsuario();
                                            ArrayList<Usuarios> listaUsuarios = daousu.listarTodo();                                        
                                            for (Usuarios usu : listaUsuarios) {
                                                if (usu.getIdPerfil() == 2) {
                                                    out.print("<option value=" + usu.getIdUsuario() + ">" + usu.getNombre() + "</option>");
                                                }

                                            }
                                        %>

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Nombre Alumno</label>
                                    <input type="text" class="form-control" name="nombrealumno" placeholder="Nombre Alumno..." />
                                </div>

                                <div class="form-group">
                                    <label>Rut Alumno</label>
                                    <input type="text" class="form-control" name="rutalumno" oninput="checkRut(this)" placeholder="Rut Alumno..." maxlength="9" />
                                </div>
                                <div class="form-group">
                                    <label>Carreras</label>
                                    <select class="form-control" name="carreraalumno">
                                        <option value="" disabled selected>Seleccione una Carrera</option>
                                        <%
                                            ArrayList<Carreras> carr = dao.traerCarreras();
                                            for (Carreras car : carr) {
                                                out.print("<option>" + car.getNombreCarrera() + "</option>");
                                            }
                                        %>

                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Jornada</label>
                                    <select class="form-control" name="idjornada">
                                        <% DAOAlumno daoal = new DAOAlumno();
                                            ArrayList<Jornada> listaJornada = daoal.listarTodo();
                                            for (Jornada jor : listaJornada) {
                                                out.print("<option value=" + jor.getId_jornada() + ">" + jor.getNom_jornada() + "</option>");
                                            }
                                        %>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label>Correo Alumno</label>
                                    <input type="email" class="form-control" name="correoalumno" placeholder="Correo..." />
                                </div>
                                <div class="form-group">
                                    <label>Telefono Alumno</label>
                                    <input type="tel" class="form-control" name="telefono" placeholder="912345678 " maxlength="9" />
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputFile" >Seleccionar archivo</label>
                                    
                                    <!-- CAMBIAR, HACER QUE ARCHIVOS SE QUITEN LOS ESPACIOS-->
                                    <input type="file" name="file" >
                                    
                                </div>

                                <div class="box-footer">
                                    <button type="submit" class="btn btn-primary">Enviar</button>
                                </div>

                            </form>
                        </div>
                        <!-- /.box-body -->
                    </div>


                    <!--------------------------
                    | Your Page Content Here |
                    -------------------------->

                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->
        </div>
        <!-- Main Footer -->
        <footer class="main-footer">
            <!-- To the right -->
            <div class="pull-right hidden-xs">
                Anything you want
            </div>
            <!-- Default to the left -->
            <strong>Copyright &copy; 2016 <a href="#">Company</a>.</strong> All rights reserved.
        </footer>
        
        <!-- Bootstrap 3.3.7 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- AdminLTE App -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.15/js/adminlte.min.js"></script>
        <!-- Optionally, you can add Slimscroll and FastClick plugins.
                     Both of these plugins are recommended to enhance the
                     user experience. -->
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>
        <script>
            $('#formulario').bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    nombresolicitud: {
                        validators: {
                            stringLength: {
                                min: 5,
                                message: 'Por favor ingresar el nombre completo de la solicitud con un mínimo de 5 caracteres',
                                max: 300
                            },
                            notEmpty: {
                                message: 'Por favor ingresar un nombre'
                            }
                        }
                    },
                    descripcion: {
                        validators: {
                            stringLength: {
                                min: 20,
                                message: 'Por favor ingrese una descripción de la solicitud con un mínimo de 20 caracteres',
                                max: 2000
                            },
                            notEmpty: {
                                message: 'Toda solicitud necesita una descripción'
                            }
                        }
                    },
                    nombrealumno: {
                        validators: {
                            stringLength: {
                                min: 10,
                                message: 'El nombre del alumno debe contener al menos 10 caracteres ',
                                max: 150
                            },
                            notEmpty: {
                                message: 'Toda solicitud debe contener nombre del alumno para enviar el correo'
                            }
                        }
                    },
                    rutalumno: {
                        validators: {
                            stringLength: {
                                min: 8,
                                message: 'El rut debe contener un mínimo de 8 dígitos'
                            },
                            regexp: {
                                regexp: /^\d{8,9}[0-9kK]{1}$/,
                                message: 'El rut debe tener un formato de números más el dígito verificador, sin puntos ni guion.'
                            },
                            notEmpty: {
                                message: 'Toda solicitud debe contener un rut de alumno para enviar el correo'
                            }
                        }
                    },
                    correoalumno: {
                        validators: {
                            stringLength:{
                                max:200
                            },                            
                            notEmpty: {
                                message: 'Por favor ingrese una dirección de correo electrónico'
                            },
                            emailAddress: {
                                message: 'Por favor ingrese una dirección de correo electrónico valido'
                            }
                        }
                    },
                    file: {
                        validators: {
                            notEmpty: {
                                message: 'Se tiene que ingresar un archivo obligatoriamente'
                            }
                        }
                    },

                    telefono: {
                        validators: {
                            regexp: {
                                regexp: /^\d{7,8}[0-9kK]{1}$/,
                                message: 'Ingresar el numero completo con el formato 912345678'
                            },
                            notEmpty: {
                                message: 'Por favor ingrese el numero telefonico'
                            }
                        }
                    },
                   tipoflujo: {                        
                        validators: {
                            notEmpty: {
                                message: 'Por favor seleccione un tipo de flujo'
                        }
                        }
                    },
                    idusuario: {                        
                        validators: {
                            notEmpty: {
                                message: 'Por favor seleccione un resposanble'
                        }
                    }
                    },
                    carreraalumno: {                        
                        validators: {
                            notEmpty: {
                                message: 'Por favor seleccione una carrera'
                        }
                    }
                    }
                }
            });
            
            function checkRut(rut) {
                // Despejar Puntos
                var valor = rut.value.replace('.', '');
                // Despejar Guión
                valor = valor.replace('-', '');

                // Aislar Cuerpo y Dígito Verificador
                cuerpo = valor.slice(0, -1);
                dv = valor.slice(-1).toUpperCase();

                // Formatear RUN
                rut.value = cuerpo + dv

                // Si no cumple con el mínimo ej. (n.nnn.nnn)
                if (cuerpo.length < 7) {
                    rut.setCustomValidity("RUT Incompleto");
                    return false;
                }

                // Calcular Dígito Verificador
                suma = 0;
                multiplo = 2;

                // Para cada dígito del Cuerpo
                for (i = 1; i <= cuerpo.length; i++) {

                    // Obtener su Producto con el Múltiplo Correspondiente
                    index = multiplo * valor.charAt(cuerpo.length - i);

                    // Sumar al Contador General
                    suma = suma + index;

                    // Consolidar Múltiplo dentro del rango [2,7]
                    if (multiplo < 7) {
                        multiplo = multiplo + 1;
                    } else {
                        multiplo = 2;
                    }

                }

                // Calcular Dígito Verificador en base al Módulo 11
                dvEsperado = 11 - (suma % 11);

                // Casos Especiales (0 y K)
                dv = (dv == 'K') ? 10 : dv;
                dv = (dv == 0) ? 11 : dv;

                // Validar que el Cuerpo coincide con su Dígito Verificador
                if (dvEsperado != dv) {
                    rut.setCustomValidity("RUT Inválido");
                    return false;
                }

                // Si todo sale bien, eliminar errores (decretar que es válido)
                rut.setCustomValidity('');
            }

            

        </script>
        <script>
            function filtro() {

                var idflujovar = $('#tipoflujo').val();
                $.ajax({
                    type: 'POST',
                    url: "FiltrarRes",
                    data: {
                        id: idflujovar
                    },
                    success: function (result) {
                        $("#idusuario").html(result);

                    }
                });

            }
        </script>
    </body>

</html>
