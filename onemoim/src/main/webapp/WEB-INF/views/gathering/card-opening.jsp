<%@ page import="com.hana.onemoim.member.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<% MemberDto loggedInMember = (MemberDto) session.getAttribute("loggedInMember"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/card-opening.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-wrapper">
            <p class="page-name">모임카드개설</p>
            <form action="/gathering/card-opening" method="post">

                <input type="hidden" name="memberId" value="${loggedInMember.memberId}"/>
                <input type="hidden" name="simplePassword" value="${loggedInMember.simplePassword}"/>
                <input type="hidden" name="gatheringId" value="${gatheringId}"/>
                <input type="hidden" name="gatheringName" value="${gatheringName}"/>
                <input type="hidden" name="accountNumber" value="${accountNumber}"/>


                <div class="box-container">
                    <p class="header-text">
                        하나원모임<br/>모임 카드 개설
                    </p>
                    <p class="sub-text">
                        통장 개설을 위해 아래 약관에 동의해주세요
                    </p>
                    <div class="essential-terms-container">
                        <p class="essential-terms">필수약관</p>
                        <div class="essential-terms-titles">
                            <div class="essential-terms-title1">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]체크카드 개인회원 약관</p>
                            </div>
                            <div class="essential-terms-title2">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]현금카드 약관</p>
                            </div>
                            <div class="essential-terms-title3">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]특정 금융거래 보고 및 이용에 관한 안내</p>
                            </div>
                            <div class="essential-terms-title4">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]하나원모임 모임카드 상품 설명서</p>
                            </div>
                            <div class="essential-terms-title5">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]하나원모임 모임카드 특약</p>
                            </div>
                            <div class="essential-terms-title6">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[필수]개인(신용)정보 수집, 이용, 제공 동의</p>
                            </div>
                            <div class="essential-terms-title7">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>개인(신용)정보 및 금융거래정보<br/>
                                제 3자 제공 동의서[모임카드서비스이용]</p>
                            </div>
                        </div>
                    </div>
                    <div class="optional-terms-container">
                        <p class="optional-terms">선택약관</p>
                        <div class="optional-terms-titles">
                            <div class="optional-terms-title1">
                                <img src="${pageContext.request.contextPath}/img/checkbox_empty.png"
                                     class="checkbox-toggle" alt="checkbox_empty"/>
                                <p>[선택]해외 부정거래 방지 정보제공 동의</p>
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
<jsp:include page="../includes/account-opening-modal1.jsp"/>
<jsp:include page="../includes/account-opening-modal2.jsp"/>
<jsp:include page="../includes/account-opening-modal3.jsp"/>
<jsp:include page="../includes/account-opening-modal4.jsp"/>
<jsp:include page="../includes/account-opening-modal5.jsp"/>
<jsp:include page="../includes/account-opening-modal6.jsp"/>
<jsp:include page="../includes/account-opening-modal7.jsp"/>
<jsp:include page="../includes/account-opening-modal8.jsp"/>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/account-opening.js"></script>
</body>
</html>
