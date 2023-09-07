<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>서비스소개</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/service-introduction.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">서비스 소개</p>
            </div>
            <img src="/img/introduction01.jpg" alt="소개1" class="service-introduction">
            <img src="/img/introduction02.png" alt="소개2" class="service-introduction-02">

        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/gathering-info.js"></script>
</body>
</html>
