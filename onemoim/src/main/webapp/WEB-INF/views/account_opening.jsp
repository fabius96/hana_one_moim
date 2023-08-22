<%@ page import="com.hana.onemoim.member.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<% MemberDto loggedInMember = (MemberDto) session.getAttribute("loggedInMember"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account_opening.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-wrapper">
            <p class="page-name">통장개설</p>
            <form action="/account_opening" method="post">

                <input type="hidden" name="memberId" value="${loggedInMember.memberId}"/>
                <input type="hidden" name="simplePassword" value="${loggedInMember.simplePassword}"/>
                <input type="hidden" name="accountNickname" value="${productName}"/>

                <div class="box-container">
                    <p class="header-text">
                        ${productName}<br>통장 개설
                    </p>
                    <p class="sub-text">
                        통장 개설을 위해 아래 약관에 동의해주세요
                    </p>
                    <div class="essential-terms-container">
                        <p class="essential-terms">필수약관</p>
                        <div class="essential-terms-titles">
                            <div class="essential-terms-title1">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]전자금융거래 기본약관</p>
                            </div>
                            <div class="essential-terms-title2">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]개인(신용)정보 수집, 이용 동의서[비여신 금융거래]</p>
                            </div>
                            <div class="essential-terms-title3">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]KBC 올크레딧 서비스 이용약관(신상정보서비스)</p>
                            </div>
                            <div class="essential-terms-title4">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]하나은행 통장 상품 설명서</p>
                            </div>
                            <div class="essential-terms-title5">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]하나은행 통장 특약</p>
                            </div>
                            <div class="essential-terms-title6">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]개인정보 제3자 제공 동의서</p>
                            </div>
                            <div class="essential-terms-title7">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]고유식별정보 수집, 이용 동의서</p>
                            </div>
                        </div>
                    </div>
                    <div class="optional-terms-container">
                        <p class="optional-terms">선택약관</p>
                        <div class="optional-terms-titles">
                            <div class="optional-terms-title1">
                                <img src="img/checkbox_empty.png" class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[선택]개인(신용)정보 수집, 이용, 제공 동의서[상품서비스 안내 등]</p>
                            </div>
                        </div>
                    </div>
                    <div class="essential-terms-box">
                        <p id="check-all-essential">필수 약관 모두 체크하기</p>
                    </div>
                    <div class="optional-terms-box">
                        <p id="check-all-optional">선택 약관 모두 체크하기</p>
                    </div>
                    <div class="submit-box">
                        <button type="submit" class="button-text">개설하기</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

</div>
<jsp:include page="includes/account_opening_modal1.jsp"/>
<jsp:include page="includes/account_opening_modal2.jsp"/>
<jsp:include page="includes/account_opening_modal3.jsp"/>
<jsp:include page="includes/account_opening_modal4.jsp"/>
<jsp:include page="includes/account_opening_modal5.jsp"/>
<jsp:include page="includes/account_opening_modal6.jsp"/>
<jsp:include page="includes/account_opening_modal7.jsp"/>
<jsp:include page="includes/account_opening_modal8.jsp"/>
<jsp:include page="includes/footer.jsp"/>
<script src="/js/account_opening.js"></script>
</body>
</html>
