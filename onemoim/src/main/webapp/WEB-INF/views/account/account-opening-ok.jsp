<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>통장개설성공</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-opening-ok.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-container">
            <div class="text-container">
                <p class="signup_ok_title">금융상품을 <br/>개설해주셔서 감사합니다.</p>
                <p class="signup_ok_content">
                    통장개설이 성공적으로 완료되었습니다.<br/>
                    하나원모임과 함께해서 즐거운 금융생활을 시작해보세요.
                </p>
            </div>
        </div>
        <div class="button-container">
            <button type="button" class="inquiry-button">
                <a href="/account-info-hana" class="button-text">계좌조회</a>
            </button>
            <button type="button" class="main-button">
                <a href="/account/after-login-main" class="button-text">메인</a>
            </button>
        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
