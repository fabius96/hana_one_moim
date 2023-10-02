<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>loading</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/openbanking-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="modal-electronic-transactions1">
    <div class="modal-content">
        <img src="${pageContext.request.contextPath}/img/loading.gif" alt="로딩스피너" class="loading-spinner">
        <div class="text-area">
            <p class="notice-text">다른 금융기관 내 계좌를 한 번에 불러오는 중입니다.</p>
        </div>
    </div>
</div>

<script src="/js/openbanking-modal.js"></script>
</body>
</html>
