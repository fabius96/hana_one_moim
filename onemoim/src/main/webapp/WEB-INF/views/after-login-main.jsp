<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>after_login_main</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/after-login-main.css">
</head>
<body>

<div class="except-footer">
    <jsp:include page="includes/header_after_login.jsp"/>
    <div class="main-container">

        <div class="sub-container">
            <div class="container">
                <img src="<%= request.getContextPath() %>/img/main.png"/>
                <p class="slogan">
                    <span>새로운 모임을 만들고 </span><br/>
                    <span>친구들과 추억을 나눠보세요</span>
                </p>
                <p class="hash-tag">#가족 #학교, 동아리 #취미, 동호회 #스터디 #회사, 팀</p>
            </div>

            <div class="create-group-button">
                <button type="submit" class="button-text" onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-create'">모임 만들기</button>
            </div>

            <div class="boxes-container">
                <div class="big-link-button" id="link-button-group">
                    <button type="button" class="link-button-text" id="link-button-text-group" onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-info'">모임</button>
                </div>

                <div class="big-link-button" id="link-button-openbanking">
                    <button type="submit" class="link-button-text" id="link-button-text-openbanking">오픈뱅킹</button>
                </div>
            </div>
        </div>

    </div>

    <div class="menu-container">
        <div class="menu" id="first-menu">
            <a href="${pageContext.request.contextPath}/account/account-info-hana" class="link-button">
                <p class="menu-title">계좌조회</p>
                <p class="menu-describe">계좌정보 거래내역 조회</p>
            </a>
        </div>
        <div class="menu">
            <a href="${pageContext.request.contextPath}/account/account-transfer-hana" class="link-button">
                <p class="menu-title">계좌이체</p>
                <p class="menu-describe">계좌이체부터 회비납부까지!</p>
            </a>
        </div>
        <div class="menu">
            <a href="${pageContext.request.contextPath}/gathering/gathering-info" class="link-button">
                <p class="menu-title">모임</p>
                <p class="menu-describe">모임원들과 함께!</p>
            </a>
        </div>
        <div class="menu" id="last-menu">
            <a href="/" class="link-button">
                <p class="menu-title">오픈뱅킹</p>
                <p class="menu-describe">내 모든 계좌를 한 눈에</p>
            </a>
        </div>
    </div>

</div>

<jsp:include page="includes/footer.jsp"/>
</body>
</html>