// 커뮤니티 메인 페이지로 이동
function goToHome(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}`;
}

// 커뮤니티 모임관리 페이지로 이동
function goToInfo(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/info`;
}

// 커뮤니티 알정 페이지로 이동
function goToCalendar(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/calendar`;
}

// 커뮤니티 갤러리 페이지로 이동
function goToGallery(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/gallery`;
}

// 커뮤니티 계좌 페이지로 이동
function goToAccount(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/account`;
}
