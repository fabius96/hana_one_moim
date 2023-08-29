<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>거래내역조회</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-transaction.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <p class="page-name">거래내역조회</p>

            <div class="dropdown-container" data-dropdown="account">
                <input type="hidden" name="accountNumber" id="selectedAccountNumber" value="">
                <span class="dropdown-title">&nbsp 계좌를 선택하세요</span>
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

            <table class="transaction-table">
                <thead>
                <tr>
                    <th class="left-title">거래일시</th>
                    <th>유형</th>
                    <th>적요</th>
                    <th>출금액</th>
                    <th>입금액</th>
                    <th class="right-title">잔액</th>
                </tr>
                </thead>
                <tbody class="transaction-content">
                <%-- 데이터가 여기에 들어갈 예정입니다. --%>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>

<script src="/js/account-transaction.js"></script>
</body>
</html>
