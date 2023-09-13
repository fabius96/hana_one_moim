$(document).ready(function() {
    // 모달 닫기
    $(".close-btn, .close").on("click", closeCurrentModal);

    // 이미지 아이템 클릭
    $(".image-item").on("click", function() {
        const postId = $(this).data('post-id');
        const gatheringId = $(this).data('gathering-id');

        // Ajax 요청
        $.ajax({
            url: `/community/${gatheringId}/gallery-post?postId=${postId}`,
            type: 'GET',
            success: function(response) {
                // 게시글 제목, 내용 바인딩
                $(".modal-content-writer").text(response.gatheringMemberName);
                $(".modal-content-createdat").text(response.createdAt);
                $(".modal-content-title").text(response.title);
                $(".modal-content-view-count").text(response.viewCnt);
                $(".modal-content-content").text(response.content);

                // 이미지 목록 동적 추가
                const ImgContent = $(".post-detail-modal-content .modal-image-container");
                ImgContent.empty(); // 기존 이미지 삭제

                response.imageUrlList.forEach((imageUrl, index) => {
                    const imgTag = $("<img>").attr("src", imageUrl)
                        .addClass("modal-image-item")
                        .attr("data-index", index)
                        .on('load', function() {
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

                $('#post-detail-modal').show(); // 모달 보이기
            },
            error: function(error) {
                console.error("게시글 로딩 실패", error);
            }
        });
    });

    // 이전 버튼 클릭
    $("#prevBtn").click(function() {
        switchImage(-1);
    });

    // 다음 버튼 클릭
    $("#nextBtn").click(function() {
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
