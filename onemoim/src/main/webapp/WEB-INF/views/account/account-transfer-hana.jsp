<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-transfer-hana.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">계좌이체</p>
                <a href="${pageContext.request.contextPath}/account/account-transfer-hana" class="chosen-page">하나은행</a>
                <a href="${pageContext.request.contextPath}/account/account-transfer-other"
                   class="unchosen-page">다른은행</a>
            </div>

            <div class="content-wrapper">

                <div><p class="content-name">출금정보</p></div>
                <div><p class="sub-content-name">하나은행에서 출금</p></div>

                <div class="dropdown-container" data-dropdown="account">
                    <span class="dropdown-title">계좌를 선택하세요</span>
                    <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="dropdown-arrow" alt="화살표">
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
                    <input class="password-input" type="password" maxlength="6" placeholder="오픈뱅킹 비밀번호 6자리 입력">
                </div>

            </div>
            <div class="second-content-wrapper">
                <div><p class="content-name">입금정보</p></div>
                <div class="input-container">
                    <input class="password-input" type="text" maxlength="6" placeholder="입금금액">
                    <span class="input-currency">원</span>
                </div>

                <div class="bank-account-wrapper">

                    <div class="dropdown-container bank-dropdown" data-dropdown="bank">
                        <span class="dropdown-title">은행을 선택하세요</span>
                        <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="dropdown-arrow" alt="화살표">
                        <div class="dropdown-menu">
                            <div class="dropdown-item">
                                <span class="bank-name">하나은행</span>
                            </div>
                            <div class="dropdown-item">
                                <span class="bank-name">신한은행</span>
                            </div>
                        </div>
                    </div>

                    <div class="input-container account-input-container account-box">
                        <input class="password-input" type="text" maxlength="14" placeholder="계좌번호 입력">
                    </div>

                </div>

            </div>
        </div>
    </div>
</div>


<jsp:include page="../includes/footer.jsp"/>
<script src="/js/account-transfer-hana.js"></script>
</body>
</html>
