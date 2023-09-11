<%@ page import="com.hana.onemoim.gathering.dto.GatheringDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- scriptlet -->
<jsp:include page="scriptlet/header_after_login_scriptlet.jsp"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/header-community.css">
</head>
<body>

<div class="second-header">
    <p class="second-menu-text">
        <img src="<%= request.getContextPath() %>/img/home.png" alt="home" data-gathering-id="${gathering.gatheringId}" onclick="goToHome(this)"/>
    </p>
    <p class="second-menu-text" id="gallery">
        <a href="/gathering/service-introduction">갤러리</a>
    </p>
    <p class="second-menu-text" id="calendar">
        <a href="/account/account-info-hana">일정</a>
    </p>
    <p class="second-menu-text" id="account">
        <a href="/account/account-transfer-hana">계좌</a>
    </p>
    <p class="second-menu-text" id="card">
        <a href="/account/account-product-list">카드</a>
    </p>
    <p class="second-menu-text" id="gathering">
        <a data-gathering-id="${gathering.gatheringId}" onclick="goToInfo(this)">모임관리</a>
    </p>
</div>
<script src="/js/header-community.js"></script>
</body>
</html>
