<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카드 혜택 추천</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/card-benefit-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="modal-electronic-transactions">
    <div class="modal-content">

        <span class="close">&times;</span>

        <p class="modal-title">카드 혜택 추천</p>

        <div class="modal-info">
            <img src="${pageContext.request.contextPath}/img/character4.png" alt="별돌이4" class="character-image">
            <p>최근 6개월 간의 모임 카드 지출 내역을 바탕으로 카드 혜택을 추천해드립니다. </p>
        </div>

        <div class="modal-footer">
            <button class="agree-btn">가입신청</button>
            <button class="close-btn">닫기</button>
        </div>
    </div>
</div>
<script src="/js/card-benefit-modal.js"></script>
</body>
</html>
