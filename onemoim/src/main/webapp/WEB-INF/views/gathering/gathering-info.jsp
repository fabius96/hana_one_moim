<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>모임조회</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gathering-info.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
        <div class="main-container">
            <div class="outer-content-wrapper">
                <div class="header-container">
                    <p class="page-name">내 모임</p>
                    <button type="button" class="create-gathering-button">
                        <a href="${pageContext.request.contextPath}/gathering/gathering-create" class="button-text">새로운 모임 만들기</a>
                    </button>
                </div>
            </div>
        </div>
</div>
    <jsp:include page="../includes/footer.jsp"/>
</body>
</html>
