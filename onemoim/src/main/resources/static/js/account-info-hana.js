// 회원가입 성공 alert
document.addEventListener("DOMContentLoaded", function(event) {
    // 현재 URL에서 'signupSuccess=true' 쿼리 매개변수 확인
    const urlParams = new URLSearchParams(window.location.search);
    const accountOpenSuccess = urlParams.get('accountOpenSuccess');

    if (accountOpenSuccess === "true") {
        alert('통장개설이 완료되었습니다.');
    }
});