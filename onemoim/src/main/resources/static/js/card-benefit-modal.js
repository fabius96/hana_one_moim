$(document).ready(function() {
    // 클릭 이벤트 등록
    $(".recommend-button").on("click", function(event) {
        console.log("클릭");
        event.stopPropagation();
        $("#modal-electronic-transactions").show();
    });

    $(".close-btn, .close").on("click", closeCurrentModal);
});

function closeCurrentModal() {
    $("#modal-electronic-transactions").hide();
}
