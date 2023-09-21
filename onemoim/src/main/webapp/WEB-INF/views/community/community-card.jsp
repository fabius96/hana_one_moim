<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 카드</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/community-card.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}" data-card-transactions="${cardTransactionMap}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="page-name-wrapper">
                <p class="page-name">모임카드</p>
                <div class="page-name-right">
                    <button class="benefit-button" id="benefit-button"
                            onclick="location.href='${pageContext.request.contextPath}/gathering/card-opening-setting'">
                        카드혜택 변경하기</button>
                </div>
            </div>
            <div class="card-container">
                <div class="card-container-left">
                    <img src="<%= request.getContextPath() %>/img/card.png" alt="원모임카드" class="card-image">
                </div>
                <div class="card-container-right">
                    <P class="card-title"> ${gathering.gatheringName} 모임카드</P>
                    <div class="card-benefit-area">
                        <c:forEach var="benefit" items="${cardBenefit}">
                            <div class="benefit-row">
                                <div class="benefit-image-area">
                                    <c:set var="imgSrc">
                                        <c:choose>
                                            <c:when test="${benefit.benefitName == '마트'}">benefit_mart.png</c:when>
                                            <c:when test="${benefit.benefitName == '베이커리'}">benefit_bakery.png</c:when>
                                            <c:when test="${benefit.benefitName == '하나페이 맛집'}">benefit_resturant.png</c:when>
                                            <c:when test="${benefit.benefitName == '대중교통'}">benefit_transport.png</c:when>
                                            <c:when test="${benefit.benefitName == '주유/LPG 충전'}">benefit_oil.png</c:when>
                                            <c:when test="${benefit.benefitName == '커피'}">benefit_coffee.png</c:when>
                                            <c:when test="${benefit.benefitName == '편의점'}">benefit_conbin.png</c:when>
                                            <c:when test="${benefit.benefitName == '딜리버리'}">benefit_delivery.png</c:when>
                                            <c:when test="${benefit.benefitName == '병원/약국'}">benefit_hospital.png</c:when>
                                            <c:when test="${benefit.benefitName == '온라인식품/쇼핑'}">benefit_shopping.png</c:when>
                                            <c:otherwise>card.png</c:otherwise>
                                        </c:choose>
                                    </c:set>
                                    <img src="<%= request.getContextPath() %>/img/${imgSrc}" alt="카드혜택"
                                         class="benefit-image">
                                </div>
                                <div class="benefit-item">
                                    <p class="benefit-item-p">${benefit.benefitName} 할인 ${benefit.benefitRate}%</p>
                                        ${benefit.benefitDescription} <br>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
            <p class="area-name"><%= Calendar.getInstance().get(Calendar.MONTH) %>월 지출분석</p>
            <div class="chart-area">
                <div class="canvas-container">
                    <canvas id="myDoughnutChart"></canvas>
                </div>
                <table id="legend-table" class="legend-table">
                    <thead>
                    <tr>
                        <th></th>
                        <th>영역</th>
                        <th>금액</th>
                        <th>비율</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 이 부분은 자바스크립트에서 동적으로 채워집니다. -->
                    </tbody>
                </table>

            </div>

        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="/js/community-card.js"></script>
</body>
</html>
