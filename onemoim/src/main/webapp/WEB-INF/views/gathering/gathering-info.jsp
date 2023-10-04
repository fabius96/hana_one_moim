<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임조회</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-info.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">

        <input type="hidden" id="message" value="${message}">

        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">내 모임</p>
                <button type="button" class="create-gathering-button">
                    <a href="${pageContext.request.contextPath}/gathering/gathering-create" class="button-text">새로운 모임
                        만들기</a>
                </button>
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
                                <img src="${pageContext.request.contextPath}/img/next-arrow.png" alt="더보기"
                                     class="next-arrow" data-gathering-id="${gatherings.gatheringId}">
                                <div class="accordion-body-right">
                                    <div class="accordion-body-right">
                                        <c:choose>
                                            <c:when test="${gatherings.memberStatusCode == 71}">
                                                <button class="detail-button" style="background-color: #999999;">
                                                    <a class="button-text">승인대기</a>
                                                </button>
                                            </c:when>
                                            <c:when test="${gatherings.memberStatusCode == 72}">
                                                <button class="detail-button" style="background-color: #999999;">
                                                    <a class="button-text">활동정지</a>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="detail-button">
                                                    <a href="${pageContext.request.contextPath}/community/${gatherings.gatheringId}"
                                                       class="button-text">이동하기</a>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

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
