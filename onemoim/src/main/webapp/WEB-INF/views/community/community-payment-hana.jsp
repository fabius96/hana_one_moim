<%@ page import="com.hana.onemoim.member.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% MemberDto loggedInMember = (MemberDto) session.getAttribute("loggedInMember");%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회비납부(하나은행)</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-transfer-hana.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<input type="hidden" id="accountPassword" value="<%= loggedInMember.getSimplePassword() %>">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>

    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">회비납입</p>
                <a href="${pageContext.request.contextPath}/community/${gatheringId}/payment-hana" class="chosen-page">하나은행</a>
                <a href="${pageContext.request.contextPath}/community/${gatheringId}/payment-other"
                   class="unchosen-page">다른은행</a>
            </div>

            <form action="/community/${gatheringId}/payment-hana" method="post">

                <div class="content-wrapper">

                    <div><p class="content-name">출금정보</p></div>
                    <div><p class="sub-content-name">다른은행에서 출금</p></div>

                    <div class="dropdown-container" data-dropdown="account">
                        <input type="hidden" name="accountNumber" id="selectedAccountNumber" value="">
                        <span class="dropdown-title">계좌를 선택하세요</span>
                        <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="dropdown-arrow"
                             alt="화살표">
                        <div class="dropdown-menu">
                            <c:forEach var="account" items="${accounts}">
                                <c:choose>
                                    <c:when test="${account.accountNumber == param.accountNumber}">
                                        <div class="dropdown-item selected-account" data-balance="${account.balance}">
                                            <span class="account-nickname">${account.accountNickname}</span>
                                            <span class="account-number">${account.accountNumber}</span>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="dropdown-item" data-balance="${account.balance}">
                                            <span class="account-nickname">${account.accountNickname}</span>
                                            <span class="account-number">${account.accountNumber}</span>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>

                    <div><p class="sub-content-name">출금가능금액</p></div>
                    <div><p class="money">0원</p></div>
                    <div class="input-container">
                        <input class="password-input" type="password" maxlength="6" name="accountPassword"
                               placeholder="오픈뱅킹 비밀번호 6자리 입력">
                    </div>

                </div>
                <div class="second-content-wrapper">
                    <div><p class="content-name">입금정보</p></div>
                    <div class="content-description">우리 모임의 기본 회비 정보로 우선 설정됩니다.</div>
                    <div class="input-container">
                        <input class="password-input" type="text" placeholder="입금금액" name="amount"
                               value="${paymentAmount}">
                        <span class="input-currency">원</span>
                    </div>

                    <div class="bank-account-wrapper">

                        <div class="dropdown-container bank-dropdown" data-dropdown="bank">
                            <span class="dropdown-title">${not empty param.bankName ? param.bankName : '하나은행'}</span>
                            <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="dropdown-arrow"
                                 alt="화살표">
                            <div class="dropdown-menu">
                                <div class="dropdown-item">
                                    <span class="bank-name">하나은행</span>
                                </div>
                                <div class="dropdown-item">
                                    <span class="bank-name">국민은행</span>
                                </div>
                                <div class="dropdown-item">
                                    <span class="bank-name">농협은행</span>
                                </div>
                                <div class="dropdown-item">
                                    <span class="bank-name">신한은행</span>
                                </div>
                                <div class="dropdown-item">
                                    <span class="bank-name">우리은행</span>
                                </div>
                                <div class="dropdown-item">
                                    <span class="bank-name">IBK기업은행</span>
                                </div>
                            </div>
                        </div>

                        <div class="input-container account-input-container account-box">
                            <input class="password-input" type="text" maxlength="14" placeholder="계좌번호 입력"
                                   name="otherAccountNumber" value="${accountNumber}">
                        </div>
                    </div>

                    <div class="input-container">
                        <input class="password-input" type="text" placeholder="거래메모" name="memo">
                    </div>

                </div>
                <div class="submit-box">
                    <button type="submit" class="button-text">이체하기</button>
                </div>
            </form>

        </div>
    </div>
</div>


<jsp:include page="../includes/footer.jsp"/>
<script src="/js/account-transfer-hana.js"></script>
</body>
</html>
