<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>갤러리 글쓰기 모달</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/gallery-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="modal-electronic-transactions">
    <div class="modal-content">

        <span class="close">&times;</span>

        <p class="modal-title">게시글 작성</p>

        <form action="/community/${gatheringId}/gallery" method="post" enctype="multipart/form-data">
            <div class="form-content">
                <div class="input-container">
                    <input class="title" type="text" name="title"
                           placeholder="제목 입력 (20자 이내로 입력해주세요)" required>
                </div>
                <div class="input-container content-input-container">
                        <textarea class="content" name="content" rows="10"
                                  placeholder="내용 입력 (모임에 관한 새로운 소식을 입력해주세요)" required></textarea>
                </div>
                <div class="input-container">
                    <label for="galleryImage" class="file-upload-label">
                        <span id="file-name">이미지 업로드</span>
                        <img src="${pageContext.request.contextPath}/img/camera.png" alt="Upload Image"
                             class="upload-icon"/>
                    </label>
                    <input id="galleryImage" class="galleryImage" multiple type="file" name="galleryImage"
                           style="display: none;">
                </div>
                <p class="open-info">공지사항 여부</p>
                <p class="input-description">공지사항으로 설정 시 모임 메인 페이지에 게시됩니다.</p>
                <div class="radio-container">
                    <label>
                        <input class="isNotice" type="radio" name="isNotice" value="Y">
                        공지사항
                    </label>
                    <label>
                        <input class="isNotice" type="radio" name="isNotice" value="N">
                        일반 게시물
                    </label>
                </div>

            </div>

            <div class="modal-footer">
                <button type="submit" class="write-btn">등록</button>
                <button type="button" class="close-btn">닫기</button>
            </div>

        </form>
    </div>
</div>
<script src="/js/gallery-modal.js"></script>
</body>
</html>
