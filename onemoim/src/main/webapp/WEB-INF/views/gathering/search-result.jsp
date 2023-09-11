<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임조회</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/search-result.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">

        <input type="hidden" id="message" value="${message}">

        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">검색 결과 &nbsp; ${gatheringCount}</p>
                <form action="${pageContext.request.contextPath}/gathering/gathering-search" method="get"
                      class="search-form">
                    <input type="text" placeholder="모임 검색" class="search-bar" name="keyword" value="${keyword}">
                    <button type="submit" class="search-icon"></button>
                </form>
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
                                        <p class="gathering-leader-name-content" >
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
                                                <a href="${pageContext.request.contextPath}/community/${gatherings.gatheringId}"
                                                   class="button-text">이동하기</a>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="join-button">
                                                <a href="${pageContext.request.contextPath}/joinGathering?gatheringId=${gatherings.gatheringId}"
                                                   class="button-text">가입신청</a>
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
<script src="/js/gathering-info.js"></script>
</body>
</html>
