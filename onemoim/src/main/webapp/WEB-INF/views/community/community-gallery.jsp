<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 갤러리</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/community-gallery.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
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

<jsp:include page="../includes/gallery-modal.jsp"/>
<jsp:include page="../includes/post-detail-modal.jsp"/>
<jsp:include page="../includes/footer.jsp"/>
</body>
</html>
