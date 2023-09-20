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

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="page-name-wrapper">
                <p class="page-name">모임카드</p>
                <div class="page-name-right">
                    <button class="benefit-button" id="benefit-button">카드혜택 변경하기</button>
                </div>
            </div>
            <div class="card-container">
                <div class="card-container-left">
                    <img src="<%= request.getContextPath() %>/img/card.png" alt="원모임카드" class="card-image">
                </div>
                <div class="card-container-right">
                    ${gathering.gatheringName} 카드
                </div>
            </div>
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
