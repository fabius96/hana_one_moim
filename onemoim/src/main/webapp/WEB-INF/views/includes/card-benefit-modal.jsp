<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>카드 혜택 추천</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/card-benefit-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}"
        data-first-area="${recommendData.firstArea}"
        data-second-area="${recommendData.secondArea}"
        data-third-area="${recommendData.thirdArea}"
>
<div class="modal" id="modal-electronic-transactions">
    <div class="modal-content">

        <span class="close">&times;</span>

        <p class="modal-title">카드 혜택 추천</p>

        <div class="modal-info">
            <img src="${pageContext.request.contextPath}/img/character4.png" alt="별돌이4" class="character-image">
            <p>최근 6개월간 모임 카드 지출 총액은
                <span class="strong-span">
                    <fmt:formatNumber value="${recommendData.total}" type="number" pattern="#,##0"/></span>원으로
            </p>

            <p class="no-margin">이 중 ${recommendData.firstArea} 영역 지출이
                <span class="strong-span"><fmt:formatNumber
                        value="${recommendData.firstSum}" type="number"
                        pattern="#,##0"/></span>원(${recommendData.firstPercent}%)</p>
            <p class="no-margin"> ${recommendData.secondArea} 영역 지출이
                <span class="strong-span"><fmt:formatNumber
                        value="${recommendData.secondSum}" type="number"
                        pattern="#,##0"/></span>원(${recommendData.secondPercent}%)</p>
            <p class="no-margin"> ${recommendData.thirdArea} 영역 지출이
                <span class="strong-span"><fmt:formatNumber value="${recommendData.thirdSum}"
                                                            type="number"
                                                            pattern="#,##0"/></span>원(${recommendData.thirdPercent}%)을
                기록하였습니다.</p>

            <p>해당 기록을 바탕으로 추천드리는 카드 혜택 설정은 다음과 같습니다.</p>
            <p class="benefit-rate">${recommendData.firstArea} 5% &nbsp;&nbsp;
                ${recommendData.secondArea} 3% &nbsp;&nbsp;
                ${recommendData.thirdArea} 2% &nbsp;&nbsp;</p>


            <p>혜택 적용 시 월 평균 <span class="strong-span">
                <fmt:formatNumber value="${recommendData.discountTotal}" type="number" pattern="#,##0"/></span>원 할인
                혜택을 받아보실 수 있습니다.</p>
        </div>

        <div class="modal-footer">
            <button class="agree-btn">혜택적용</button>
            <button class="close-btn">닫기</button>
        </div>
    </div>
</div>
<script src="/js/card-benefit-modal.js"></script>
</body>
</html>
