<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오픈뱅킹</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/openbanking.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}" data-account='${accountDtoList}'>
<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">

        <div class="header-container">
            <p class="page-name">오픈뱅킹</p>
        </div>
        <div class="content-container">
            <div class="content-header">
                <p class="content-name">등록계좌</p>
            </div>

            <table class="account-table">
                <thead>
                <tr>
                    <th class="left-title">상품명</th>
                    <th>금융기관</th>
                    <th>계좌번호</th>
                    <th class="right-title">선택</th>
                </tr>
                </thead>
                <tbody class="account-content">
                </tbody>
            </table>

            <div class="button-wrapper">
                <div class="button-container">
                    <button class="delete-button">연결해제</button>
                    <button class="registration-button">계좌추가</button>
                </div>
            </div>

        </div>
    </div>


</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="/js/openbanking.js"></script>
</body>
</html>
