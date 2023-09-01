<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/card-opening-setting.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-wrapper">
            <p class="page-name">카드</p>

            <form action="/gathering/card-opening-setting" method="post">
                <div class="box-container">
                    <p class="header-text">
                        카드 혜택 설정
                    </p>
                    <p class="sub-text">
                        카드 혜택을 설정해주세요. <br/>
                        총 10개 영역에서 선택 영역 할인율이 총합 10%가 되도록 설정해주세요.<br/>
                        선택 영역에는 제한이 없지만(최대 10개까지 가능), 1개 영역에 대한 할인율은 1~5% 사이로 지정해주셔야 합니다.
                    </p>
                    <div class="select-area">
                        <div class="select-area-line">
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_mart.png" alt="혜택_마트"
                                     class="benefit-image">
                                <p class="benefit-title">마트</p>
                                <p class="benefit-description">이마트, 트레이더스, 홈세일 클럽,</p>
                                <p class="benefit-description">홈플러스, 롯데마트 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider1">
                                    <p><span id="sliderValue1">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_bakery.png" alt="혜택_베이커리"
                                     class="benefit-image">
                                <p class="benefit-title">베이커리</p>
                                <p class="benefit-description">파리바게트, 뚜레쥬르, 아티제,</p>
                                <p class="benefit-description">카페노티드, 던킨도너츠 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider2">
                                    <p><span id="sliderValue2">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_resturant.png" alt="혜택_하나페이맛집"
                                     class="benefit-image">
                                <p class="benefit-title">하나페이 맛집</p>
                                <p class="benefit-description">데이터기반 맛집 서비스</p>
                                <p class="benefit-description">하나페이 맛집 가맹점 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider3">
                                    <p><span id="sliderValue3">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_transport.png" alt="혜택_대중교통"
                                     class="benefit-image">
                                <p class="benefit-title">대중교통</p>
                                <p class="benefit-description">버스, 지하철</p>
                                <p class="benefit-description">청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider4">
                                    <p><span id="sliderValue4">0</span>&nbsp;%</p>
                                </div>
                            </div>
                        </div>
                        <div class="select-area-line">
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_oil.png" alt="혜택_주유/LPG충전"
                                     class="benefit-image">
                                <p class="benefit-title">주유/LPG 충전</p>
                                <p class="benefit-description">SK에너지, GS칼텍스, S-Oil</p>
                                <p class="benefit-description">현대오일뱅크, 알뜰주유소 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider5">
                                    <p><span id="sliderValue5">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_coffee.png" alt="혜택_커피"
                                     class="benefit-image">
                                <p class="benefit-title">커피</p>
                                <p class="benefit-description">스타벅스, 커피빈, 이디야, 폴바셋,</p>
                                <p class="benefit-description">투썸플레이스, 블루보틀 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider6">
                                    <p><span id="sliderValue6">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_conbin.png" alt="혜택_편의점"
                                     class="benefit-image">
                                <p class="benefit-title">편의점</p>
                                <p class="benefit-description">CU, GS25, 세븐일레븐,</p>
                                <p class="benefit-description">이마트24 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider7">
                                    <p><span id="sliderValue7">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_delivery.png" alt="혜택_딜리버리"
                                     class="benefit-image">
                                <p class="benefit-title">딜리버리</p>
                                <p class="benefit-description">배달의민족, 요기요,</p>
                                <p class="benefit-description">쿠팡이츠 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider8">
                                    <p><span id="sliderValue8">0</span>&nbsp;%</p>
                                </div>
                            </div>
                        </div>
                        <div class="select-area-line">
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_hospital.png" alt="혜택_병원/약국"
                                     class="benefit-image">
                                <p class="benefit-title">병원/약국</p>
                                <p class="benefit-description">병원 및 약국</p>
                                <p class="benefit-description">청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider9">
                                    <p><span id="sliderValue9">0</span>&nbsp;%</p>
                                </div>
                            </div>
                            <div class="benefit-box">
                                <img src="${pageContext.request.contextPath}/img/benefit_shopping.png" alt="혜택_온라인식품&쇼핑"
                                     class="benefit-image">
                                <p class="benefit-title">온라인식품&쇼핑</p>
                                <p class="benefit-description">컬리, SSG.COM, 롯데ON,</p>
                                <p class="benefit-description">더현대닷컴 청구할인</p>
                                <div class="slider-area">
                                    <input type="range" min="0" max="5" value="0" class="slider" id="slider10">
                                    <p><span id="sliderValue10">0</span>&nbsp;%</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="card-setting-button">
                    <a class="button-text">혜택 설정하기</a>
                </button>
            </form>

        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/card-opening-setting.js"></script>
</body>
</html>
