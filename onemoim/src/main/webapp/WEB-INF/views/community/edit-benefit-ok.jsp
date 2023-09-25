<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>계좌이체성공</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-opening-ok.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>

    <div class="main-container">
        <div class="content-container">
            <div class="text-container">
                <p class="signup_ok_title">카드 혜택 변경이 완료되었습니다.</p>
                <p class="signup_ok_content">
                    카트 혜택 변경이 성공적으로 완료되었습니다.<br/>
                    추가적인 작업이 필요하시다면 아래 버튼을 선택해주세요.
                </p>
            </div>
        </div>
        <div class="button-container">
            <button type="button" class="inquiry-button">
                <a href="${pageContext.request.contextPath}/community/${gatheringId}/card-benefit" class="button-text">혜택조회</a>
            </button>
            <button type="button" class="main-button">
                <a href="${pageContext.request.contextPath}/community/${gatheringId}" class="button-text">모임메인</a>
            </button>
        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
