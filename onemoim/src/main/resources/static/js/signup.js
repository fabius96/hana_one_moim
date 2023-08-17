// id input validator
document.getElementById("loginId").addEventListener("invalid", function(event) {
    if (event.target.validity.valueMissing) {
        event.target.setCustomValidity("아이디를 입력하세요.");
    } else {
        event.target.setCustomValidity(""); // 기본 메시지로 재설정
    }
});

// password input validator
document.getElementById("password").addEventListener("invalid", function(event) {
    if (event.target.validity.valueMissing) {
        event.target.setCustomValidity("비밀번호를 입력하세요.");
    } else {
        event.target.setCustomValidity(""); // 기본 메시지로 재설정
    }
});

$(document).ready(function() {
    $("#double-check-button").click(function(event) {
        event.preventDefault(); // form submit 방지
        let loginId = $("#loginId").val();
        $.get("/api/checkLoginId", { loginId: loginId }, function(data) {
            if(data) {
                alert("이미 사용중인 아이디입니다.");
            } else {
                alert("사용 가능한 아이디입니다.");
            }
        });
    });
});
