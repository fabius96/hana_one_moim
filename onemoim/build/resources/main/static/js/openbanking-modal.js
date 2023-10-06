$(document).ready(function() {
    // 계좌추가 버튼 클릭 이벤트
    $(".registration-button").on("click", function() {
        $("#modal-electronic-transactions1").fadeIn();

        // 2초 후에 특정 페이지로 이동

        setTimeout(function() {
            window.location.href = '/openbanking/account-info';
        }, 2000); // 2000 밀리초 후 (2초 후)
    });
});