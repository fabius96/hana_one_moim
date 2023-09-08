// 모임 메인 페이지로 이동
function home(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    console.log(gatheringId);
    window.location.href = `/community/community-main?gatheringId=${gatheringId}`;
}
