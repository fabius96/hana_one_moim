<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>계좌조회</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-info-hana.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">

        <div class="content-wrapper">
            <div class="header-container">
                <p class="page-name">계좌조회</p>
                <a href="${pageContext.request.contextPath}/account/account-info-hana" class="chosen-page">하나은행</a>
                <a href="${pageContext.request.contextPath}/account/account-info-other" class="unchosen-page">다른은행</a>
            </div>

            <div class="custom-accordion" id="customAccordion">
                <c:forEach var="account" items="${accounts}" varStatus="status">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading${status.index}">
                            <button class="accordion-button" type="button"
                                    onclick="toggleCustomAccordion('collapse${status.index}')" aria-expanded="true"
                                    aria-controls="collapse${status.index}">
                                <span class="account-nickname-text"> ${account.accountNickname}</span>
                                <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="button-img">
                            </button>
                        </h2>
                        <div id="collapse${status.index}" class="accordion-collapse collapse"
                             aria-labelledby="heading${status.index}" data-parent="#customAccordion">
                            <div class="accordion-body">
                                <p><span class="account-number">${account.accountNumber}</span></p>
                                <div class="accordion-body-right">
                                    <p class="money">${account.balance}원</p>
                                    <button class="transaction-button">
                                        <a href="${pageContext.request.contextPath}/account/account-transfer-hana?accountNumber=${account.accountNumber}"
                                           class="button-text">이체</a>
                                    </button>
                                    <button class="history-button"><a
                                            href="${pageContext.request.contextPath}/account/account-transaction"
                                            class="button-text">거래내역</a></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="/js/account-info-hana.js"></script>
</body>
</html>
