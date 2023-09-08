$(document).ready(function() {
    // 클릭 이벤트 등록
    $(".accordion-body-center, .next-arrow, .second-area-item-title ,.second-area-item-description").on("click", function(event) {
        event.stopPropagation();

        let gatheringId = $(this).data("gathering-id");
        fetchGatheringData(gatheringId);
    });

    $(".close-btn, .close").on("click", closeCurrentModal);
});

function fetchGatheringData(gatheringId) {
    $.ajax({
        url: `/gathering/gathering-modal-info?gatheringId=${gatheringId}`,
        type: 'GET',
        success: function(data) {
            populateModalWithData(data, gatheringId);
            $("#modal-electronic-transactions").fadeIn();
        },
        error: function() {
            alert("모임 정보를 가져오는 데 실패했습니다.");
        }
    });
}

function closeCurrentModal() {
    $(this).closest(".modal").hide();
}

function populateModalWithData(data, gatheringId) {
    $('#modalGatheringName').text(data.gatheringName);
    $('#modalGatheringDescription').text(data.gatheringDescription);
    $('#modalGatheringLeaderName').text(data.gatheringLeaderName);
    $('#modalGatheringImage').attr('src', data.gatheringCoverImageUrl);

    populateInterests(data.interestList);

    setupActionButton(data.joined, gatheringId);
}

function populateInterests(interestList) {
    let $interestsContainer = $('#modalInterestsContainer');
    $interestsContainer.empty();

    interestList.forEach(interest => {
        let interestElement = `
            <div class="interest-box">
                <p class="interest-box-content">${interest}</p>
            </div>`;
        $interestsContainer.append(interestElement);
    });
}

function setupActionButton(joined, gatheringId) {
    let $agreeBtn = $(".agree-btn");
    let contextPath = "${pageContext.request.contextPath}";
    if (joined) {
        $agreeBtn.text("이동하기").off("click").on("click", function() {
            window.location.href = `/community/${gatheringId}`;
        });
    } else {
        $agreeBtn.text("가입신청").off("click").on("click", function() {
            window.location.href = `${contextPath}/joinGathering?gatheringId=${gatheringId}`;
        });
    }
}
