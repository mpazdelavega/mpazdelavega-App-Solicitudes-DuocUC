<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="main-header">
    <!-- Logo -->
    <a href="home.jsp" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>G</b>DC</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>Gestión</b>Documental</span>
    </a>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Notifications Menu -->
                <li class="dropdown notifications-menu">
                    <!-- Menu toggle button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning">
                            <c:out value="${cantNotif}"/>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">
                            <c:choose>
                                <c:when test="${cantNotif == 0}">
                                   No hay notificaciones 
                                </c:when>
                                <c:when test="${cantNotif > 0}">
                                    <a href="/solicitudesAsignadas.jsp">Tienes ${cantNotif} Solicitud/es asignadas</a>
                                </c:when>    
                            </c:choose>                   
                        </li>
                    </ul>
                </li>
                <!-- Tasks Menu -->

                <!-- User Account Menu -->
                <li class="dropdown user user-menu">
                    <!-- Menu Toggle Button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <!-- The user image in the navbar-->    
                        <!-- hidden-xs hides the username on small devices so only the image appears. -->
                        <span class="hidden-xs">
                            <c:out value="${nombreusuario}"/>
                        </span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- Menu Footer-->
                        <li class="user-footer">                                        
                            <div class="pull-right">
                                <a href="Salir" class="btn btn-default btn-flat">Cerrar Sesion</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
            </ul>
        </div>
    </nav>
</header>