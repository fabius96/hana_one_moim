<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- scriptlet -->
<jsp:include page="scriptlet/header_after_login_scriptlet.jsp"/>
<% String name = (String) request.getAttribute("name");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>header-after-login</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/header_after_login.css">
</head>
<body>

<div class="header">
    <a href="/after-login-main" class="link-button">
        <div class="logo-container">
            <img src="<%= request.getContextPath() %>/img/hana_logo.png" alt="하나금융로고"/>
            <p class="service-title">하나원모임</p>
        </div>
        <p class="menu-text" id="menu-introduce">
            <a href="/gathering/service-introduction">서비스소개</a>
        </p>
        <p class="menu-text" id="menu-view">
            <a href="/account/account-info-hana">조회</a>
        </p>
        <p class="menu-text" id="menu-transfer">
            <a href="/account/account-transfer-hana">이체</a>
        </p>
        <p class="menu-text" id="menu-products">
            <a href="/account/account-product-list">금융상품</a>
        </p>
        <p class="menu-text" id="menu-moim">
            <a href="/gathering/gathering-info">모임</a>
        </p>

        <p>
            <% if (name != null && !name.equals("Unknown")) { %>
            <a href="/" class="member-name"><%= name %> 님</a> <a href="/logout" class="logout-text">로그아웃</a>
            <% } else { %>
            <a href="/signin" class="logout-text">로그인</a>
            <% } %>
        </p>
</div>

</body>
</html>
