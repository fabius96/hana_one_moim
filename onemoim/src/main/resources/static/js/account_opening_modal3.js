$(document).ready(function() {
    // 약관 클릭 이벤트
    $(".essential-terms-title3 p").on("click", function() {
        $("#modal-electronic-transactions3").fadeIn();
    });

    // 모달 닫기 이벤트
    $(".close").on("click", function() {
        $(".modal").fadeOut();
    });

    // 모달의 동의 버튼 클릭 이벤트
    $(".agree-btn").on("click", function() {
        // 현재 모달의 ID를 가져옴
        var modalId = $(this).closest(".modal").attr("id");

        // 해당 모달의 연관된 체크박스 이미지를 변경
        if (modalId == "modal-electronic-transactions3") {
            $(".essential-terms-title3 .checkbox-toggle").attr("src", "img/checkbox_full.png");
        }

        // 모달 닫기
        $(this).closest(".modal").hide();
    });

    // 모달의 닫기 버튼 클릭 이벤트
    $(".close-btn, .close").on("click", function() {
        $(this).closest(".modal").hide();
    });
});