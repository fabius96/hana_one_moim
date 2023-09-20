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
    <p class="second-menu-link">
        <img src="<%= request.getContextPath() %>/img/home.png" alt="home" data-gathering-id="${gatheringId}" onclick="goToHome(this)"/>
    </p>
    <p class="second-menu-link" id="gallery">
        <a data-gathering-id="${gatheringId}" onclick="goToGallery(this)">갤러리</a>
    </p>
    <p class="second-menu-link" id="calendar-schedule">
        <a data-gathering-id="${gatheringId}" onclick="goToCalendar(this)">일정</a>
    </p>
    <p class="second-menu-link" id="account">
        <a data-gathering-id="${gatheringId}" onclick="goToAccount(this)">계좌</a>
    </p>
    <p class="second-menu-link" id="card">
        <a data-gathering-id="${gatheringId}" onclick="goToCard(this)">카드</a>
    </p>
    <p class="second-menu-link" id="gathering">
        <a data-gathering-id="${gatheringId}" onclick="goToInfo(this)">모임관리</a>
    </p>
</div>
<script src="/js/header-community.js"></script>
</body>
</html>
