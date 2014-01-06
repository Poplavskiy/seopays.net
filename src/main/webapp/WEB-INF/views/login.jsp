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

                    <%--TODO Create js validation: repass and pass, reqiered email, captcha--%>
                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger">
                            <spring:message code="label.loginerror" />
                            : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                        </div>
                    </c:if>

                    <form method="POST" action="<c:url value="/${lang}/j_spring_security_check"/>" role="form">
                        <div class="form-group">
                            <label><spring:message code="label.login"/></label>
                            <input type="input" name="j_username" class="form-control" placeholder="<spring:message code="label.login"/>" value="" >
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.password"/></label>
                            <input type="password" name="j_password" class="form-control"  placeholder="<spring:message code="label.password"/>">
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.remember"/></label>
                            <input type="checkbox" name="_spring_security_remember_me" />
                        </div>
                        <div class="form-group">
                            <img id="captcha_img_id" alt="captcha image" src="/captcha"/>
                            <div style="float: right; padding-right: 10px;height: 70px;line-height: 70px;">
                                <span class="glyphicon glyphicon-repeat" onclick="refresh_captcha()"></span>
                                <span class="glyphicon-class">reload</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label><spring:message code="label.captchacode"/></label>
                            <input type="input" name="j_captcha" class="form-control" placeholder="<spring:message code="label.captchacode"/>">
                        </div>
                        <button type="submit" class="btn btn-default"><spring:message code="label.submit"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function refresh_captcha() {

        var d = new Date();
        $("#captcha_img_id").attr("src", "/captcha?"+d.getTime());
    }


</script>

<jsp:include page="modules/footer_welcome.jsp"/>


