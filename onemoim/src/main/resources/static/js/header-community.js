// 커뮤니티 메인 페이지로 이동
function goToHome(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}`;
}

// 커뮤니티 정보 페이지로 이동
function goToInfo(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/info`;
}
