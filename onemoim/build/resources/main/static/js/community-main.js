$(document).ready(function () {
    let gatheringId = $('body').data('gathering-id');

    $.ajax({
        url: `/community/${gatheringId}/interest`,
        type: 'GET',
        success: function (data) {
            populateInterests(data);
        },
        error: function () {
            alert("모임 정보를 가져오는 데 실패했습니다.");
        }
    });
});

function populateInterests(interestList) {
    let $interestsContainer = $('#interestsContainer');
    $interestsContainer.empty();

    interestList.forEach(interest => {
        let interestElement = `
                <div class="interest-box">
                    <p class="interest-box-content">${interest}</p>
                </div>`;
        $interestsContainer.append(interestElement);
    });
}


// 커뮤니티 모임관리 페이지로 이동
function goToInfo(gatheringIdParam) {
    var gatheringId = gatheringIdParam.getAttribute('data-gathering-id');
    window.location.href = `/community/${gatheringId}/info`;
}