<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 메인</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/community-main.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="slider-wrapper">
                <div class="container" id="first-container">
                    <img src="${gathering.gatheringCoverImageUrl}" alt="슬라이드이미지" class="container-image"/>
                    <div class="gathering-leader-name-box">
                        <p class="gathering-leader-name-content">
                            <img src="${pageContext.request.contextPath}/img/crown.png" alt="모임장 왕관"
                                 class="crown-img">
                            &nbsp;
                            <span>${gathering.gatheringLeaderName}</span>
                        </p>
                    </div>
                    <div class="interest-container" id="interestsContainer">
                    </div>
                    <p class="slogan">
                        <span>새로운 모임을 만들고 </span><br/>
                        <span>친구들과 추억을 나눠보세요</span>
                    </p>
                    <p class="hash-tag">#가족 #학교, 동아리 #취미, 동호회 #스터디 #회사, 팀</p>
                    <div class="create-group-button">
                        <button type="submit" class="button-text"
                                onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-create'">
                            모임만들기
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
