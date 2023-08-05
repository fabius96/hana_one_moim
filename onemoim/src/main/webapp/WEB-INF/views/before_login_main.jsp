<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>before_login_main</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/before_login_main.css">
</head>
<body>
<div class="except-footer">
    <jsp:include page="includes/header_before_login.jsp"/>

    <div class="main-container">

        <div class="background-container">
            <img src="img/background.jpg" class="main-image"/>
            <div class="overlay-content">
                <p class="slogan">
                    하나의 이야기가 만들어지는 곳,<br/>
                    하나원모임
                </p>
                <p class="description">
                    하나원모임은 모임의 순간을 기록하고 추억할 수 있는 갤러리형 커뮤니티 기반의 모임통장 서비스입니다.<br/>
                    하나원모임을 통해 연인, 가족, 친구들과 새로운 추억을 만들어보세요.
                </p>
                <div class="button-container">
                    <p class="button-text">시작하기</p>
                </div>
            </div>
        </div>

        <div class="icon-group">
            <div class="icon-block">
                <img src="img/piggy-bank.png" class="icon-left"/>
                <p class="icon-title">새로운 모임을 계획해보세요</p>
                <p class="icon-description">모든 모임원들과 잔액, 거래내역을 함께 볼 수 있어
                    <br/>투명한 회비 운영이 가능합니다.
                    <br/>일자, 카테고리 별 소비 분석을 통하여 효과적인 예산 설정과
                    <br/>지출 확인이 가능합니다</p>
            </div>
            <div class="icon-block">
                <img src="img/photo.png" class="icon-center"/>
                <p class="icon-title">소중한 순간을 추억해보세요</p>
                <p class="icon-description">갤러리형 커뮤니티 서비스를 통하여 모임원들과 추억을 쌓고,
                    <br/>지난 추억을 회상할 수 있습니다.</p>
            </div>
            <div class="icon-block">
                <img src="img/calender.png" class="icon-right"/>
                <p class="icon-title">새로운 모임을 계획해보세요</p>
                <p class="icon-description">캘린더를 통하여 직관적인 일정 관리가 가능합니다.
                    <br/>새로운 모임 일정을 등록하고 모임원들과 공유해보세요.</p>
            </div>
        </div>

    </div>


</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
