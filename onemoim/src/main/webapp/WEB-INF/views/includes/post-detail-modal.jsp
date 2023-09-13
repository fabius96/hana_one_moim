<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>갤러리 게시글 모달</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/post-detail-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="post-detail-modal">
    <div class="post-detail-modal-content">
        <div class="left-content">
            <button id="prevBtn" class="scroll-btn">
                <img src="<%= request.getContextPath() %>/img/modal-pre-button.png" class="scroll-btn-img"
                     alt="좌측화살표">
            </button>
            <div class="modal-image-container">
            </div>
            <button id="nextBtn" class="scroll-btn">
                <img src="<%= request.getContextPath() %>/img/modal-next-button.png" class="scroll-btn-img"
                     alt="우측화살표">
            </button>
        </div>
        <div class="right-content">
            <div class="right-content-header">
                <span class="modal-content-writer"> </span>
                ·
                <span class="modal-content-createdat">날짜</span>
            </div>
            <span class="more">···</span>
            <span class="close">&times;</span>
            <div class="right-content-area">
                <div class="modal-content-title">제목영역</div>
                <div class="modal-content-info">
                    <img src="<%=request.getContextPath()%>/img/view.png" alt="조회수" class="view-count">
                    <span class="modal-content-view-count"></span>
                    <img src="<%=request.getContextPath()%>/img/comment.png" alt="댓글수" class="comment-count">
                    <span class="modal-content-comment-count">0</span>
                    <div class="modal-content-content"></div>
                </div>
            </div>
            <div class="right-comment-area">
                <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div>
                <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div>
                <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div> <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div> <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div> <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div> <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div> <div class="modal-content-comment">
                    <span class="modal-content-writer modal-content-comment-writer"> </span>
                    ·
                    <span class="modal-content-createdat modal-content-comment-createdat">날짜</span>
                    <p class="modal-content-comment-content">댓글 내용이 이런 식으로 달릴거에요</p>
                </div>
                <p>\n</p>
            </div>
            <div class="right-comment-form-area">
                <form>
                    <input type="text" placeholder="댓글을 입력해주세요" class="comment-input">
                    <button class="comment-button">등록</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/js/post-detail-modal.js"></script>
</body>
</html>
