$(document).ready(function () {
    // 모달 닫기
    $(".close-btn, .close").on("click", closeCurrentModal);

    // 댓글 등록 버튼 클릭 이벤트
    $(".comment-button").on("click", function () {
        const postId = $(".image-item").data('post-id');
        const gatheringId = $(".image-item").data('gathering-id');
        const content = $(".comment-input").val();

        if (content.trim() === "") {
            alert("댓글을 입력하세요.");
            return;
        }

        $.ajax({
            url: `/community/${gatheringId}/gallery/comment`,
            type: 'POST',
            data: {
                postId: postId,
                content: content
            },
            success: function (response) {
                // alert(response.message);
                $(".comment-input").val(''); // 댓글 입력 필드 초기화

                // 새로운 댓글을 화면에 추가
                const newComment = `
                    <div class="modal-content-comment">
                     <div class="comment-info">
                       <div class="comment-writer-info">
                        <span class="modal-content-writer modal-content-comment-writer">${response.memberName || 'Your Name Here'}</span>
                        ·
                        <span class="modal-content-createdat modal-content-comment-createdat">방금 전</span>
                       </div>
                        <span class="comment-actions">수정&nbsp;&nbsp;&nbsp;삭제</span>
                       </div>
                       <p class="modal-content-comment-content">${content}</p>
                    </div>`;
                $(newComment).prependTo(".comment-area"); // 새 댓글을 맨 위에 추가
            },
            error: function () {
                console.error("댓글 등록 실패");
                alert("댓글 등록에 실패했습니다.");
            }
        });
    });

    // 이미지 아이템 클릭
    $(".image-item").on("click", function () {
        const postId = $(this).data('post-id');
        const gatheringId = $(this).data('gathering-id');
        const gatheringMemberId = $(this).data('gathering-member-id');

        // Ajax 요청
        $.ajax({
            url: `/community/${gatheringId}/gallery-post?postId=${postId}`,
            type: 'GET',
            success: function (response) {
                // 게시글 제목, 내용 바인딩
                $(".modal-content-writer").text(response.gatheringMemberName);
                $(".modal-content-createdat").text(response.createdAt);
                $(".modal-content-title").text(response.title);
                $(".modal-content-view-count").text(response.viewCnt);
                $(".modal-content-comment-count").text(response.commentCnt);
                $(".modal-content-content").text(response.content);

                // 이미지 목록 동적 추가
                const ImgContent = $(".post-detail-modal-content .modal-image-container");
                ImgContent.empty(); // 기존 이미지 삭제

                response.imageUrlList.forEach((imageUrl, index) => {
                    const imgTag = $("<img>").attr("src", imageUrl)
                        .addClass("modal-image-item")
                        .attr("data-index", index)
                        .on('load', function () {
                            if (index === 0) {
                                $(this).addClass('active');
                            }
                        });
                    ImgContent.append(imgTag);
                });

                // 이미지 한 개 시 버튼 숨기기
                if (response.imageUrlList.length <= 1) {
                    $("#prevBtn, #nextBtn").hide();
                } else {
                    $("#prevBtn, #nextBtn").show();
                }

                // 댓글 목록 동적 추가
                const commentArea = $(".comment-area");
                // 먼저 기존 댓글들을 모두 제거
                commentArea.empty();

                response.galleryCommentDtoList.forEach(comment => {
                    let actionButtons = '';
                    if (comment.gatheringMemberId == gatheringMemberId) {
                        actionButtons = '<span class="comment-actions">수정&nbsp;&nbsp;&nbsp;삭제</span>';
                    }
                    console.log(actionButtons);
                    const commentElement = `
                        <div class="modal-content-comment">
                         <div class="comment-info">
                           <div class="comment-writer-info">
                             <span class="modal-content-writer modal-content-comment-writer">${comment.memberName}</span>
                            ·
                            <span class="modal-content-createdat modal-content-comment-createdat">${comment.createdAt}</span>
                          </div>
                          ${actionButtons}
                         </div>
                        
                         <p class="modal-content-comment-content">${comment.content}</p>
                        </div>`;
                    commentArea.append(commentElement);
                });

                $('#post-detail-modal').show(); // 모달 보이기
            },
            error: function (error) {
                console.error("게시글 로딩 실패", error);
            }
        });
    });

    // 이전 버튼 클릭
    $("#prevBtn").click(function () {
        switchImage(-1);
    });

    // 다음 버튼 클릭
    $("#nextBtn").click(function () {
        switchImage(1);
    });

    // 이미지 전환 함수
    function switchImage(direction) {

        const currentImage = $(".modal-image-item.active");
        const currentIndex = currentImage.data("index");
        console.log(currentIndex);
        const nextIndex = currentIndex + direction;

        if (nextIndex >= 0 && nextIndex < $(".modal-image-item").length) {
            currentImage.removeClass("active");
            $(`.modal-image-item[data-index="${nextIndex}"]`).addClass("active");
        }
    }

    // 모달 닫기 함수
    function closeCurrentModal() {
        $(this).closest(".modal").hide();
    }
});
