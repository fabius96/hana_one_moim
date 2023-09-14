<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="modal-electronic-transactions">
    <div class="modal-content">

        <span class="close">&times;</span>

        <p class="modal-title">모임정보</p>

        <div class="gathering-index">
            <img id="modalGatheringImage" src="#" alt="모임 대표 이미지" class="gathering-image">
            <div class="modal-gathering-leader-name-box">
                <p class="gathering-leader-name-content">
                    <img src="${pageContext.request.contextPath}/img/crown.png" alt="모임장 왕관"
                         class="crown-img">
                    &nbsp;
                    <span id="modalGatheringLeaderName"></span>
                </p>
            </div>
            <div class="interest-container" id="modalInterestsContainer">

            </div>
            <p id="modalGatheringName" class="modal-gathering-name"></p>
        </div>

        <p id="modalGatheringDescription" class="modal-gathering-description"></p>
        <div class="modal-footer">
            <button class="agree-btn">가입신청</button>
            <button class="close-btn">닫기</button>
        </div>
    </div>
</div>
<script src="/js/gathering-modal.js"></script>
</body>
</html>
