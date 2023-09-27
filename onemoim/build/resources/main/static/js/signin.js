// id input validator
document.getElementById("loginId").addEventListener("invalid", function(event) {
    if (event.target.validity.valueMissing) {
        event.target.setCustomValidity("아이디를 입력하세요.");
    } else {
        event.target.setCustomValidity(""); // 기본 메시지로 재설정
    }
});

// password input validator
document.getElementById("memberPassword").addEventListener("invalid", function(event) {
    if (event.target.validity.valueMissing) {
        event.target.setCustomValidity("비밀번호를 입력하세요.");
    } else {
        event.target.setCustomValidity(""); // 기본 메시지로 재설정
    }
});

document.addEventListener("DOMContentLoaded", function() {
    var errorMessage = document.getElementById("error-message").value;
    if (errorMessage) {
        alert(errorMessage);
    }
});