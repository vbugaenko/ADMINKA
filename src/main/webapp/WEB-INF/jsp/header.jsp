<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>НАУКА | Успеваемость студентов онлайн</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<c:url value="/resources/imgs/favicon.ico"/>" rel="shortcut icon" type="image/x-icon">
    <link href="<c:url value="/resources/css/reset.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">


    <script src="<c:url value="/resources/js/jquery-1.8.2.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.maskedinput-1.3.js"/>"></script>
    <script src="<c:url value="/resources/js/script.js"/>"></script>
    <script>
        jQuery(function ($) {
            $.mask.definitions['~'] = '[+-]';
            $('.phone').mask('+7(999) 999-99-99');
        });
    </script>

    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="bg"></div>
<div class="wrap">
    <div class="container">
        <header>
            <div class="wrapper">

                <a href="/" class="logo"></a>
                <div class="sys_name">Успеваемость студентов</div>
                <sec:authorize access="hasAnyRole('ROLE_STUDENT','ROLE_TEACHER','ROLE_HEADTEACHER','ROLE_ADMIN')">
                <div class="user">
                    <span>user: <sec:authentication property="principal.username" />
                    </span>
                    <a href="<c:url value="/static/j_spring_security_logout"/>" class="btn">Выйти</a>
                </div>
                </sec:authorize>
            </div>
        </header>

        <div class="clear"></div>

        <section>
            <div class="wrapper">
		