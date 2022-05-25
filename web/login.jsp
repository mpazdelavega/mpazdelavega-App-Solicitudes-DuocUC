<%-- 
    Document   : login
    Created on : 11 may. 2020, 12:42:48
    Author     : Seba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    //&& session.getAttribute("notificaciones") != null
    if (session.getAttribute("nombreusuario")!= null) {
        response.sendRedirect("home.jsp");
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Gestión Documental</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <link href="https://unpkg.com/ionicons@4.5.5/dist/css/ionicons.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.4.10/css/AdminLTE.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/skins/flat/blue.css  ">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
        <style>
            body{
                background-image: url("https://aadcdn.msauthimages.net/dbd5a2dd-biut7bmjmhmf9emyp7wkwcpvbzylt7zqgrsogr52t5y/logintenantbranding/0/illustration?ts=635895201527944967");
                background-repeat: no-repeat,no-repeat;
                background-position: center center,center center;
                background-size: cover,cover;

            }
            .principal{
                width: 100%;
                height: 100%;
                background: rgba(0,0,0,0.55);
                position: absolute;

            }
            .login-logo a{
                color: white!important;

            }
            .login-box{
                margin-top: 14%;
            }


        </style>
    </head>
    <body>
        <div class="principal">
            <div class="login-box " >
                <div class="login-logo">
                    <a href="#"><b>Gestión</b>  Documental</a>
                </div>
                <!-- /.login-logo -->
                <div class="login-box-body">
                    <p class="login-box-msg">Iniciar Sesión</p>

                    <form role="form" action="Ingresar" method="post" id="formulario">
                        <div class="form-group has-feedback">
                            <input type="text" class="form-control" name="correo" placeholder="Usuario">
                            <span class="glyphicon glyphicon-user form-control-feedback"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" class="form-control" name="password" placeholder="Contraseña">
                            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                            </div>
                            <!-- /.col -->
                            <div class="col-xs-4">
                                <button type="submit" class="btn btn-primary btn-block btn-flat">Entrar </button>
                            </div>
                            <!-- /.col -->
                        </div>
                    </form>
                    
                <!-- Comprobacion y muestra de error de inicio de Sesion --> 
                <%     
                    if (request.getAttribute("Error") != (null) && (Boolean)request.getAttribute("Error") == true ){
                        out.print("<div style='text-align: center;'>"
                                + "</br>"
                                + "<p style='color: red'> Nombre de Usuario o Contraseña incorrecto </p>"
                                + "</div>"
                        );
                    }
                %>
                </div>
                <!-- /.login-box-body -->
            </div>
            <!-- /.login-box -->
        </div>
        <!-- jQuery 3 -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <!-- Bootstrap 3.3.7 -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- iCheck -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/iCheck/1.0.2/icheck.min.js"></script>
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.2/js/bootstrapValidator.min.js"></script>

        <script>
            $('#formulario').bootstrapValidator({
                
                fields: {
                    correo: {
                        validators: {
                            stringLength: {
                                min: 5,
                                message: 'por favor ingresar un usuario con un minimo de 4 caracteres'
                            },
                            notEmpty: {
                                message: 'Por favor ingresar un usuario'
                            }
                        }
                    },
                    password: {
                        validators: {
                            stringLength: {
                                min: 3,
                                message: 'El largo de la contraseña es demaciado corto'
                            },
                            notEmpty: {
                                message: 'Por favor ingresa una contraseña'
                            }
                        }
                    }
                }
            }
            );
        </script>
    </body>
</html>
