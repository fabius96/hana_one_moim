// 필수약관 체크 확인
function allEssentialChecked() {
    var allChecked = true;
    var contextPath = $("body").data("context-path");
    $(".essential-terms-container .checkbox-toggle").each(function () {
        if ($(this).attr("src") !== contextPath + "/img/checkbox_full.png") {
            allChecked = false;
            return false;
        }
    });
    return allChecked;
}

// 선택약관 체크 확인
function allOptionalChecked() {
    var allChecked = true;
    var contextPath = $("body").data("context-path");
    $(".optional-terms-container .checkbox-toggle").each(function () {
        if ($(this).attr("src") !== contextPath + "/img/checkbox_full.png") {
            allChecked = false;
            return false;
        }
    });
    return allChecked;
}

$(document).ready(function () {
    var contextPath = $("body").data("context-path");

    // 약관 체크박스 토글 기능
    $(".checkbox-toggle").on("click", function () {
        if ($(this).attr("src") === contextPath + "/img/checkbox_empty.png") {
            $(this).attr("src", contextPath + "/img/checkbox_full.png");
        } else {
            $(this).attr("src", contextPath + "/img/checkbox_empty.png");
        }
    });

    // 필수 약관 모두 체크하기
    $("#check-all-essential").on("click", function () {
        if (allEssentialChecked()) {
            $(".essential-terms-container .checkbox-toggle").attr("src", contextPath + "/img/checkbox_empty.png");
        } else {
            $(".essential-terms-container .checkbox-toggle").attr("src", contextPath + "/img/checkbox_full.png");
        }
    });

    // 선택 약관 모두 체크하기
    $("#check-all-optional").on("click", function () {
        if (allOptionalChecked()) {
            $(".optional-terms-container .checkbox-toggle").attr("src", contextPath + "/img/checkbox_empty.png");
        } else {
            $(".optional-terms-container .checkbox-toggle").attr("src", contextPath + "/img/checkbox_full.png");
        }
    });

    // form 제출 시 필수 약관 체크 확인
    $("form").on("submit", function (event) {
        if (!allEssentialChecked()) {
            alert("모든 필수 약관에 동의해주세요.");
            event.preventDefault();  // form 제출 중단
        }
    });
});
