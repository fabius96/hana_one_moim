<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입성공</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/signup-ok.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="except-footer">
    <jsp:include page="includes/header_before_login.jsp"/>

    <div class="main-container">
        <div class="content-container">
            <div class="text-container">
                <p class="signup_ok_title">하나원모임 회원으로<br/>가입해주셔서 감사합니다.</p>
                <p class="signup_ok_content">
                    회원가입 절차가 모두 완료되었습니다. <br/>
                    이제 하나원모임에서 제공되는 모든 서비스를 정상적으로 이용하실 수 있습니다.
                </p>
            </div>
        </div>
        <button type="button" class="login-button">
            <a href="/signin" class="button-text">로그인</a>
        </button>
    </div>

</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
