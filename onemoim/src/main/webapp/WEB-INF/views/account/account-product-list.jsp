<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>금융상품</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/account-product-list.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <p class="page-name">금융상품</p>
        <p class="tab-name">
            <span class="selected-tab-name">입출금이 자유로운 예금</span>
            &nbsp; &nbsp;
            <span>정기 예금</span>
            &nbsp; &nbsp; &nbsp;
            <span>적금</span>
            &nbsp; &nbsp; &nbsp;
            <span>외화상품</span>
            &nbsp; &nbsp; &nbsp;
            <span>신탁</span>
            &nbsp; &nbsp; &nbsp;
            <span>한시상품/ELD</span>
        </p>

        <div class="sub-container">
            <div class="product-list">
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">영하나플러스 통장</p>
                        <p class="product-description">젊은 그대, 당신을 위한 Must Have 통장</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="영하나플러스통장">개설하기</button>
                </div>
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">급여하나 통장</p>
                        <p class="product-description">급여 하나면 된다! 급여실적 하나로 수수료우대 혜택과 만35세이하 청년직장인을 위한 우대금리가 제공되는
                            월급통장!</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="급여하나 통장">개설하기</button>
                </div>
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">연금하나 통장</p>
                        <p class="product-description">연금 하나면 된다! 국민연금, 기초(노령)연금 등 매월 받는 연금통장으로 딱 맞는 통장!</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="연금하나 통장">개설하기</button>
                </div>
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">주거래하나 통장</p>
                        <p class="product-description">이체거래 하나면 된다! 카드대금, 공과금 등 매월 간단한 이체거래로 수수료 면제 서비스가 제공되는 우리집 생활비
                            통장!</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="주거래하나 통장">개설하기</button>
                </div>
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">하나 플러스 통장</p>
                        <p class="product-description">우대 금리도 받고, 수수료는 무제한 면제되는 통장</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="하나 플러스 통장">개설하기</button>
                </div>
                <div class="product-item">
                    <div class="product-info">
                        <p class="product-name">하나 취업이룸 통장</p>
                        <p class="product-description">구직자의 취업 촉진을 위해 지급하는 구직촉진수당, 취업활동비용, 취업성공수당의 수급권리를 보호하기 위한 압류방지
                            전용통장</p>
                    </div>
                    <button type="button" class="open-button" data-product-name="하나 취업이룸 통장">개설하기</button>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/account-product-list.js"></script>
</body>
</html>
