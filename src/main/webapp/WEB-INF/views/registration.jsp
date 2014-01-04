<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="modules/header_welcome.jsp"/>

<style type="text/css">


    #sidebar-wrapper {
        width: 25%;
        background: #101010;
        height: 220px;
        float: left;
    }

    #page-content-wrapper {
        float: left;
        width: 75%;
    }

    .sidebar-nav {
        top: 0;
        width: 250px;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .sidebar-nav li {
        line-height: 40px;
        text-indent: 20px;
    }

    .sidebar-nav li a {
        color: #999999;
        display: block;
        text-decoration: none;
    }

    .sidebar-nav li a:hover {
        color: #fff;
        background: rgba(255, 255, 255, 0.2);
        text-decoration: none;
    }

    .sidebar-nav li a:active,
    .sidebar-nav li a:focus {
        text-decoration: none;
    }

    .sidebar-nav > .sidebar-brand {
        height: 65px;
        line-height: 60px;
        font-size: 18px;
    }

    .sidebar-nav > .sidebar-brand a {
        color: #999999;
    }

    .sidebar-nav > .sidebar-brand a:hover {
        color: #fff;
        background: none;
    }

    .content-header {
        height: 65px;
        line-height: 65px;
    }

    .content-header h1 {
        margin: 0;
        margin-left: 20px;
        line-height: 65px;
        display: inline-block;
    }

    #menu-toggle {
        display: none;
    }

    .inset {
        padding-left: 20px;
        padding-top: 20px;
    }

    @media (max-width:767px) {

        #wrapper {
            padding-left: 0;
        }

        #sidebar-wrapper {
            left: 0;
        }

        #wrapper.active {
            position: relative;
            left: 250px;
        }

        #wrapper.active #sidebar-wrapper {
            left: 250px;
            width: 250px;
            transition: all 0.4s ease 0s;
        }

        #menu-toggle {
            display: inline-block;
        }

        .inset {
            padding: 15px;
        }

    }

    .shift_t {
        height: 90px;
    }

    .panel-default {
        width: 100%;
    }

    .reg_border {
        margin-left: 20px;
    }

</style>

<div class="shift_t"></div>


<div id="wrapper" class="container">

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
        <%--TODO Create info tab with normal icon--%>
        <ol class="breadcrumb reg_border">
            <li><a href="#">Home</a></li>
            <li><a href="#">Account</a></li>
            <li class="active">Registration</li>
        </ol>
        <!-- Keep all page content within the page-content inset div! -->
        <div class="page-content inset">

            <div class="panel panel-default">
                <div class="panel-body">

                    <%--TODO Create js validation: repass and pass, reqiered email, captcha--%>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                        ${error}
                        </div>
                    </c:if>

                    <form method="POST" action="<c:url value="/registration" />" role="form">
                        <div class="form-group">
                            <label><spring:message code="label.email"/></label>
                            <input type="input" name="username" class="form-control" placeholder="<spring:message code="label.enteremail"/>" value="${username}" >
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.password"/></label>
                            <input type="password" name="password" class="form-control"  placeholder="<spring:message code="label.password"/>">
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.replaypassword"/></label>
                            <input type="password" class="form-control" placeholder="<spring:message code="label.replaypassword"/>">
                        </div>
                        <div class="form-group">
                            <img alt="captcha image" src="captcha"/>
                            <%--TODO Create refresh button--%>
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.captchacode"/></label>
                            <input type="input" name="captcha" class="form-control" placeholder="<spring:message code="label.captchacode"/>">
                        </div>
                        <button type="submit" class="btn btn-default"><spring:message code="label.submit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="modules/footer_welcome.jsp"/>


