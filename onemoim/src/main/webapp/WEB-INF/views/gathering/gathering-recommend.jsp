<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임추천</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-recommend.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="header-container">
                <p class="page-name">모임 추천</p>
            </div>
            <div class="second-recommend-area">
                <c:forEach var="gatherings" items="${gatherings}">
                    <div class="second-area-item">
                        <img src="${gatherings.gatheringCoverImageUrl}" alt="${gatherings.gatheringName} Cover Image"
                             class="second-area-item-img">
                        <div class="second-area-item-content">
                            <div class="second-area-item-title" data-gathering-id="${gatherings.gatheringId}">
                                    ${gatherings.gatheringName}
                            </div>
                            <div class="second-area-item-description" data-gathering-id="${gatherings.gatheringId}">
                                    ${gatherings.gatheringDescription}
                            </div>
                            <button class="button-container" data-gathering-id="${gatherings.gatheringId}"
                                    onclick="applicationGathering('${gatherings.gatheringId}')">
                                <a class="area-button-text">가입신청</a>
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/gathering-modal.jsp"/>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
