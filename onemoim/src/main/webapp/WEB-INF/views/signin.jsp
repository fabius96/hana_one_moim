<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>signin</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/signin.css">
</head>
<body>
<div class="except-footer">
    <jsp:include page="includes/header_before_login.jsp"/>

    <div class="main-container">
        <div class="container">
            <p class="title">
                <span class="title-part1">하나 </span>
                <span class="title-part2">원</span><span class="title-part3">모임</span>
            </p>

            <img src="img/character1.png" class="image-star">

            <p class="member-text">
                <span class="normal-text">아직 회원이 아니신가요? </span>
                <a href="signup" class="link-button">
                    <span class="highlight-text">회원가입</span>
                </a>
            </p>

            <form>
                <div class="id-input">
                    <label for="memberId" class="hidden-label">id :</label>
                    <input type="text" placeholder="아이디 입력" id="memberId" name="memberId" class="input-field">
                </div>

                <div class="password-input">
                    <label for="memberPassword" class="hidden-label">id :</label>
                    <input type="password" placeholder="비밀번호 입력" id="memberPassword" name="memberPassword"
                           class="input-field">
                </div>
                <div class="login-button">
                    <button type="submit" class="button-text">로그인</button>
                </div>
            </form>

            <div class="kakao-login-button">
                <img src="img/speech-bubble.png" class="icon-image">
                <p class="button-text">카카오 로그인</p>
            </div>
        </div>
    </div>


</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
