<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- scriptlet -->
<jsp:include page="scriptlet/header_after_login_scriptlet.jsp"/>
<% String name = (String) request.getAttribute("name");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page Title</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/header_after_login.css">
</head>
<body>

<div class="header">
    <a href="/account/after-login-main" class="link-button">
        <div class="logo-container">
            <img src="<%= request.getContextPath() %>/img/hana_logo.png" alt="하나금융로고"/>
            <p class="title">하나원모임</p>
        </div>
    </a>
    <a href="/account/account-info-hana" class="menu-text menu-view">조회</a>
    <a href="/account/account-transfer-hana" class="menu-text menu-transfer">이체</a>
    <a href="/account/account-product-list" class="menu-text menu-products">금융상품</a>
    <a href="/gathering/gathering-info" class="menu-text menu-moim">모임</a>
    <p class="logout-text">
        <% if (name != null && !name.equals("Unknown")) { %>
        <a href="/" class="member-name"><%= name %> 님</a> <a href="/api/member/logout">로그아웃</a>
        <% } else {  %>
        <a href="/signin">로그인</a>
        <% } %>
    </p>
</div>

</body>
</html>
