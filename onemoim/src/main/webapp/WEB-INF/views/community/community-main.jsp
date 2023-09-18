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

<body data-context-path="${pageContext.request.contextPath}" data-gathering-id="${gatheringId}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="top-container">
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
                        <div class="gathering-member-number-box">
                            <p class="gathering-member-number-content">
                                <img src="${pageContext.request.contextPath}/img/member.png" alt="모임원"
                                     class="crown-img">
                                &nbsp;
                                <span>${gathering.gatheringMemberNumber}</span>
                            </p>
                        </div>
                        <div class="interest-container" id="interestsContainer">
                        </div>
                        <div class="gathering-name">
                            ${gathering.gatheringName}
                        </div>
                        <div class="gathering-description">
                            ${gathering.gatheringDescription}
                        </div>
                        <div class="community-info-button">
                            <button type="submit" class="button-text"
                                    data-gathering-id="${gatheringId}" onclick="goToInfo(this)">
                                모임정보
                            </button>
                        </div>
                    </div>
                </div>
                <div class="right-container">
                    <div class="boxes-container">
                        <div class="big-link-button" id="link-button-calendar">
                            <button type="button" class="link-button-text" id="link-button-text-calendar"
                                    onclick="location.href='${pageContext.request.contextPath}/community/${gatheringId}/calendar'">
                                일정보기
                            </button>
                        </div>

                        <div class="big-link-button" id="link-button-payment">
                            <button type="submit" class="link-button-text" id="link-button-text-payment">회비납부</button>
                        </div>
                    </div>
                    <div class="notice-container">
                        <div class="notice-title">
                            <span>공지사항</span>
                            <span class="notice-more">···</span>
                        </div>
                        <c:forEach var="noticeData" items="${noticeData}">
                            <div class="notice-content"
                                 data-post-id="${noticeData.postId}" data-gathering-id="${gatheringId}"
                                 data-member-id="${memberId}" data-gathering-member-id="${gatheringMemberId}">
                                <span>${noticeData.title}</span>
                                <img src="${pageContext.request.contextPath}/img/notice-arrow.png" alt="다음화살표"
                                     class="arrow-img">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


            <!-- 갤러리 영역 -->
            <div class="background-container">
                <div class="gallery-container">
                    <div class="page-name-wrapper">
                        <p class="page-name">갤러리</p>
                        <div class="page-name-right">
                            <button class="write-button" id="write-button">글쓰기</button>
                        </div>
                    </div>
                    <div class="image-container">
                        <c:forEach var="imageInfo" items="${galleryImageData}">
                            <img src="${imageInfo.imageUrl}" alt="갤러리이미지" class="image-item"
                                 data-post-id="${imageInfo.postId}" data-gathering-id="${gatheringId}"
                                 data-member-id="${memberId}" data-gathering-member-id="${gatheringMemberId}">
                        </c:forEach>
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                        <img src="<%= request.getContextPath() %>/img/sample.jpg" alt="갤러리이미지" class="image-item">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/community-main.js"></script>
<jsp:include page="../includes/gallery-modal.jsp"/>
<jsp:include page="../includes/post-detail-modal.jsp"/>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
