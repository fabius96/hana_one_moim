<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임분류</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-category.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">모임 분류</p>
            </div>

            <div class="recommend-area">
                <div class="recommend-container">
                    <button id="prevBtn" class="scroll-btn">
                        <img src="<%= request.getContextPath() %>/img/left-arrow.png" class="scroll-btn-img"
                             alt="좌측화살표">
                    </button>
                    <div class="recommend-slider">
                        <div class="recommend-item" id="recommend-item-1" onclick="showGatheringCategory('취미/동호회');">
                            <img src="<%= request.getContextPath() %>/img/c1-hobby.jpg" class="recommend-item-img"
                                 alt="모임이미지1">
                            <p class="item-title">취미/동호회</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-2" onclick="showGatheringCategory('스터디');">
                            <img src="<%= request.getContextPath() %>/img/c2-study.jpg" class="recommend-item-img"
                                 alt="모임이미지2">
                            <p class="item-title">스터디</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-3" onclick="showGatheringCategory('게임');">
                            <img src="<%= request.getContextPath() %>/img/c3-game.jpg" class="recommend-item-img"
                                 alt="모임이미지3">
                            <p class="item-title">게임</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-4" onclick="showGatheringCategory('스포츠/레저');">
                            <img src="<%= request.getContextPath() %>/img/c4-sport.jpg" class="recommend-item-img"
                                 alt="모임이미지4">
                            <p class="item-title">스포츠/레저</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-5" onclick="showGatheringCategory('헬스/다이어트');">
                            <img src="<%= request.getContextPath() %>/img/c5-health.jpg" class="recommend-item-img"
                                 alt="모임이미지5">
                            <p class="item-title">헬스/다이어트</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-6" onclick="showGatheringCategory('음악');">
                            <img src="<%= request.getContextPath() %>/img/c6-music.jpg" class="recommend-item-img"
                                 alt="모임이미지6">
                            <p class="item-title">음악</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-7" onclick="showGatheringCategory('맛집/요리');">
                            <img src="<%= request.getContextPath() %>/img/c7-food.jpg" class="recommend-item-img"
                                 alt="모임이미지7">
                            <p class="item-title">맛집/요리</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-8" onclick="showGatheringCategory('여행/캠핑');">
                            <img src="<%= request.getContextPath() %>/img/c8-travel.jpg" class="recommend-item-img"
                                 alt="모임이미지8">
                            <p class="item-title">여행/캠핑</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-9" onclick="showGatheringCategory('종교/봉사');">
                            <img src="<%= request.getContextPath() %>/img/c9-religion.jpg" class="recommend-item-img"
                                 alt="모임이미지9">
                            <p class="item-title">종교/봉사</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-10" onclick="showGatheringCategory('문화/예술');">
                            <img src="<%= request.getContextPath() %>/img/c10-art.jpg" class="recommend-item-img"
                                 alt="모임이미지10">
                            <p class="item-title">문화/예술</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-11" onclick="showGatheringCategory('영화/애니');">
                            <img src="<%= request.getContextPath() %>/img/c11-movie.jpg" class="recommend-item-img"
                                 alt="모임이미지11">
                            <p class="item-title">영화/애니</p>
                        </div>
                        <div class="recommend-item" id="recommend-item-12" onclick="showGatheringCategory('독서');">
                            <img src="<%= request.getContextPath() %>/img/c12-book.jpg" class="recommend-item-img"
                                 alt="모임이미지12">
                            <p class="item-title">독서</p>
                        </div>
                    </div>
                    <button id="nextBtn" class="scroll-btn">
                        <img src="<%= request.getContextPath() %>/img/right-arrow.png" class="scroll-btn-img"
                             alt="우측화살표">
                    </button>
                </div>
            </div>

            <div class="custom-accordion" id="customAccordion">
                <c:forEach var="gatherings" items="${gatherings}" varStatus="status">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading${status.index}">
                            <button class="accordion-button" type="button"
                                    onclick="toggleCustomAccordion('collapse${status.index}')" aria-expanded="true"
                                    aria-controls="collapse${status.index}">
                                <span class="account-nickname-text"> ${gatherings.gatheringName}</span>
                                <img src="${pageContext.request.contextPath}/img/arrow_under.png" class="button-img">
                            </button>
                        </h2>
                        <div id="collapse${status.index}" class="accordion-collapse collapse"
                             aria-labelledby="heading${status.index}" data-parent="#customAccordion">
                            <div class="accordion-body">
                                <img src="${gatherings.gatheringCoverImageUrl}" class="gathering-image" alt="모임 대표 이미지">
                                <div class="accordion-body-center" data-gathering-id="${gatherings.gatheringId}">
                                    <div class="gathering-leader-name-box">
                                        <p class="gathering-leader-name-content">
                                            <img src="${pageContext.request.contextPath}/img/crown.png" alt="모임장 왕관"
                                                 class="crown-img">
                                            &nbsp
                                                ${gatherings.gatheringLeaderName}
                                        </p>
                                    </div>
                                    <p class="gathering-name">${gatherings.gatheringName}</p>
                                    <p class="gathering-description">${gatherings.gatheringDescription}</p>
                                </div>
                                <img src="${pageContext.request.contextPath}/img/next-arrow.png" alt="더보기" class="next-arrow" data-gathering-id="${gatherings.gatheringId}">
                                <div class="accordion-body-right">
                                    <c:choose>
                                        <c:when test="${gatherings.joined}">
                                            <button class="detail-button">
                                                <a href="${pageContext.request.contextPath}/community/community-main?gatheringId=${gatherings.gatheringId}"
                                                   class="button-text">자세히 보기</a>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="join-button" data-gathering-id="${gatherings.gatheringId}">
                                                <a class="button-text">가입신청</a>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
<jsp:include page="../includes/gathering-modal.jsp"/>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/gathering-category.js"></script>
</body>
</html>
