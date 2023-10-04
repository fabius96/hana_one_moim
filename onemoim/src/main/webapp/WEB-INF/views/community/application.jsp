<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임가입신청</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-opening-ok.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-container">
            <div class="application-text-container">
                <p class="signup_ok_title">${gathering.gatheringLeaderName}님께서 <br/>
                    ${gathering.gatheringName} 모임에 초대하셨습니다.</p>
                <p class="signup_ok_content">
                    모임 가입을 희망하신다면 가입신청 버튼을 눌러주세요.
                </p>
            </div>
        </div>
        <div class="button-container">
            <button type="button" class="inquiry-button" data-gathering-id="${gathering.gatheringId}"
                    onclick="applicationCommunity('${gathering.gatheringId}')">
                <a class="button-text">가입신청</a>
            </button>
            <button type="button" class="main-button">
                <a href="/" class="button-text">메인으로</a>
            </button>
        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/gathering-info.js"></script>
</body>
</html>
