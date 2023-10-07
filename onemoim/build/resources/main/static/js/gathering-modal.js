$(document).ready(function () {
    // 클릭 이벤트 등록
    $(".accordion-body-center, .next-arrow, .second-area-item-title ,.second-area-item-description").on("click", function (event) {
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
        success: function (data) {
            populateModalWithData(data, gatheringId);
            $("#modal-electronic-transactions").fadeIn();
        },
        error: function () {
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

    setupActionButton(data.joined, data.memberStatusCode, gatheringId);
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

function setupActionButton(joined, memberStatusCode, gatheringId) {
    let $agreeBtn = $(".agree-btn");
    let contextPath = "${pageContext.request.contextPath}";
    if (memberStatusCode === 71) {
        $agreeBtn.text("활동정지").off("click").on("click", function () {
        });
        $agreeBtn.css('background-color', '#999999');
    }else if (memberStatusCode === 72) {
        $agreeBtn.text("승인대기").off("click").on("click", function () {
        });
        $agreeBtn.css('background-color', '#999999');
    } else if (memberStatusCode === 70) {
        $agreeBtn.text("이동하기").off("click").on("click", function () {
            window.location.href = `/community/${gatheringId}`;
        });
    } else {
        $agreeBtn.text("가입신청").off("click").on("click", function () {
            applicationGathering(gatheringId);
        });
    }
}

function applicationGathering(gatheringId) {
    const contextPath = document.body.getAttribute('data-context-path');
    const url = contextPath + "/gathering/gathering-application";
    $.ajax({
        url: url,
        type: 'POST',
        data: {gatheringId: gatheringId},
        success: function () {
            alert("가입신청이 성공적으로 처리되었습니다.");
            closeCurrentModal();
            location.reload();
        },
        error: function () {
            console.log(url)
            alert("가입신청에 실패했습니다.");
        }
    });
}
