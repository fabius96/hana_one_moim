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

// 회원가입 성공 alert
document.addEventListener("DOMContentLoaded", function(event) {
    // 현재 URL에서 'signupSuccess=true' 쿼리 매개변수 확인
    const urlParams = new URLSearchParams(window.location.search);
    const signupSuccess = urlParams.get('signupSuccess');

    if (signupSuccess === "true") {
        alert('회원가입이 완료되었습니다.');
    }
});

// 로그인
$(document).ready(function() {
    $('form').on('submit', function(e) {
        e.preventDefault();

        var formData = $(this).serialize();

        $.ajax({
            url: '/signin',
            type: 'POST',
            data: formData,
            dataType: 'json',
            success: function(response) {
                if (response.status === 'error') {
                    alert(response.message);
                } else {
                    window.location.href = '/after_login_main';
                }
            }
        });
    });
});