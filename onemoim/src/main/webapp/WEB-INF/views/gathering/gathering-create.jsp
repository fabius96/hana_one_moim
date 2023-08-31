<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>account_info_hana</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-create.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-wrapper">
            <p class="page-name">모임개설</p>
            <form action="/gathering/gathering-create" method="post">

                <div class="box-container">
                    <p class="header-text">
                        하나원모임<br>신규 모임 개설
                    </p>
                    <p class="sub-text">
                        신규 모임 개설을 위한 정보를 입력해주세요.
                    </p>

                    <div class="input-container">
                        <input class="gatheringName" type="text" name="gatheringName"
                               placeholder="모임 이름 입력 (20자리 이내로 입력해주세요)" required>
                    </div>
                    <div class="input-container-2">
                        <input class="gatheringDescription" type="text" name="gatheringDescription"
                               placeholder="모임설명 (모임에 대한 간단한 소개문구를 입력해주세요)" required>
                    </div>
                    <div class="input-container-3">
                        <label for="gatheringImage" class="file-upload-label">
                            <span id="file-name">모임 대표 이미지 업로드</span>
                            <img src="${pageContext.request.contextPath}/img/camera.png" alt="Upload Image"
                                 class="upload-icon"/>
                        </label>
                        <input id="gatheringImage" class="gatheringImage" type="file" name="gatheringImage"
                               style="display: none;">
                    </div>
                    <p class="sub-title">모임 회비 정보</p>
                    <div class="input-container-4">
                        <input class="paymentAmount" type="number" name="paymentAmount"
                               placeholder="모임 회비 입력" required>
                        <span class="input-currency">원</span>
                    </div>
                    <div class="input-container-5">
                        <input class="paymentDay" type="number" name="paymentDay"
                               placeholder="모임 회비 납부일 입력 (1~30일 사이)" required>
                        <span class="input-currency">일</span>
                    </div>
                    <p class="start-day-info">모임 회비 납부 시작일 선택</p>
                    <div class="input-container-6">
                        <input class="startDay" type="date" name="startDay"
                               placeholder="모임 회비 납부 시작일 선택">
                    </div>
                    <p class="payment-cycle-info">납부 주기 선택</p>
                    <div class="input-container-7">
                        <label>
                            <input class="paymentCycle" type="radio" name="paymentCycle" value="year">
                            연
                        </label>
                        <label>
                            <input class="paymentCycle" type="radio" name="paymentCycle" value="month">
                            월
                        </label>
                        <label>
                            <input class="paymentCycle" type="radio" name="paymentCycle" value="day">
                            일
                        </label>
                    </div>

                    <div class="submit-box">
                        <button type="submit" class="button-text">개설하기</button>
                    </div>
                </div>
            </form>

        </div>
    </div>

</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/gathering-create.js"/>
</body>
</html>
