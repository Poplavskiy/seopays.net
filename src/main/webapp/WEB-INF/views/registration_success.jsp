<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="modules/header_welcome.jsp"/>

<link href="../../resources/css/registration.css" rel="stylesheet">

<div class="shift_t"></div>


<div id="wrapper" class="container">

    <%--TODO Create info tab with normal icon--%>
    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand"><a href="#">Account</a></li>
            <li><a href="/login">Login</a></li>
            <li><a href="/registration">Registration</a></li>
            <li><a href="/forgot">Forgot</a></li>
        </ul>
    </div>


    <!-- Page content -->
    <div id="page-content-wrapper">

        <div class="content-header reg_border">
            <h2>
                <a id="menu-toggle" href="#" class="btn btn-default"><i class="icon-reorder"></i></a>
                <spring:message code="label.regnewuser"/>
            </h2>
        </div>
        <%--TODO Create auto breadcrumb--%>
        <ol class="breadcrumb reg_border">
            <li><a href="/">Home</a></li>
            <li class="active">Registration</li>
        </ol>
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="alert alert-success">
                        ${message}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="modules/footer_welcome.jsp"/>




