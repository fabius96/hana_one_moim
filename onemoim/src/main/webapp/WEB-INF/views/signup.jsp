<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>signup</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/signup.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="includes/header_before_login.jsp"/>
    <div class="main-container">
        <div class="signup-container">
            <img class="character-image" src="img/character2.png">
            <p class="title"><span class="green-text">하나 </span><span class="red-text">원</span><span
                    class="green-text">모임</span></p>
            <p class="login-text">
                <span>이미 회원이신가요? </span>
                <a href="signin">
                    <span class="link-text">로그인</span>
                </a>
            </p>

            <form action="/signup" method="post">
                <div class="id-input">
                    <label for="loginId" class="hidden-label">id :</label>
                    <input type="text" placeholder="아이디 입력 (6~20자리)" id="loginId" name="loginId" class="input-field"
                           required>
                </div>
                <div class="common-button" id="double-check-button">
                    <button type="button" class="button-text">중복확인</button>
                </div>

                <div class="password-input">
                    <label for="password" class="hidden-label">password :</label>
                    <input type="password" placeholder="비밀번호 입력 (숫자, 문자, 특수문자 포함 8~20자리)" id="password"
                           name="password"
                           class="input-field" required>
                </div>

                <div class="password-re-enter">
                    <label for="passwordReEnter" class="hidden-label">password-re :</label>
                    <input type="password" placeholder="비밀번호 재입력" id="passwordReEnter"
                           name="passwordReEnter"
                           class="input-field" required>
                </div>

                <div class="simple-password-input">
                    <label for="simplePassword" class="hidden-label">simple-password :</label>
                    <input type="password" placeholder="간편비밀번호 입력 (6자리)" id="simplePassword"
                           name="simplePassword"
                           class="input-field" required>
                </div>

                <div class="name-input">
                    <label for="name" class="hidden-label">name :</label>
                    <input type="text" placeholder="이름" id="name"
                           name="name"
                           class="input-field" required>
                </div>

                <div class="date-of-birth-input">
                    <label for="dateOfBirth" class="hidden-label">date-of-birth :</label>
                    <input type="text" placeholder="생년원일 입력 (8자리)" id="dateOfBirth"
                           name="dateOfBirth"
                           class="input-field" required>
                </div>

                <div class="hyphen"> -</div>

                <div class="personal-id-number-input">
                    <label for="personalIdNumber" class="hidden-label">personal-id-number :</label>
                    <input type="text" placeholder="주민번호 뒷자리 입력" id="personalIdNumber"
                           name="personalIdNumber"
                           class="input-field" required>
                </div>

                <div class="phone-number-input">
                    <label for="phoneNumber" class="hidden-label">phone-number :</label>
                    <input type="text" placeholder="휴대전화 번호 입력 ('-'제외 11자리 입력)" id="phoneNumber"
                           name="phoneNumber"
                           class="input-field" required>
                </div>

                <div class="zip-code-input">
                    <label for="zipCode" class="hidden-label">zip-code :</label>
                    <input type="text" placeholder="우편번호 입력" id="zipCode" name="zipCode" class="input-field" required>
                </div>
                <div class="common-button" id="find-zip-code-button" onclick="findZipCode()">
                    <button type="button" class="button-text">우편번호 찾기</button>
                </div>

                <div class="address-input">
                    <label for="address" class="hidden-label">address :</label>
                    <input type="text" placeholder="주소 입력" id="address"
                           name="address"
                           class="input-field" required>
                </div>

                <div class="detail-address-input">
                    <label for="detailAddress" class="hidden-label">detail-address :</label>
                    <input type="text" placeholder="상세주소 입력" id="detailAddress"
                           name="detailAddress"
                           class="input-field" required>
                </div>

                <div class="email-input">
                    <label for="email" class="hidden-label">email :</label>
                    <input type="text" placeholder="이메일주소 입력" id="email" name="email" class="input-field" required>
                </div>
                <div class="common-button" id="send-auth-mail-button">
                    <button type="button" class="button-text">인증메일 발송</button>
                </div>

                <div class="auth-code-input">
                    <label for="authCode" class="hidden-label">auth-code :</label>
                    <input type="text" placeholder="인증코드 입력" id="authCode" name="authCode" class="input-field" required>
                </div>
                <div class="common-button" id="check-auth-code-button">
                    <button type="button" class="button-text">인증코드 확인</button>
                </div>

                <div class="signup-button">
                    <button type="submit" class="button-text">회원가입</button>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
<script src="/js/signup.js"></script>

</body>

</html>
