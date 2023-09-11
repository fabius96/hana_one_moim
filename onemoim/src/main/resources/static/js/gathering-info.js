// 아코디언
function toggleCustomAccordion(elementId) {
    const contextPath = document.body.getAttribute('data-context-path');

    let allContentDivs = document.querySelectorAll('.accordion-collapse');
    allContentDivs.forEach(div => {
        let imgElement = div.previousElementSibling.querySelector('.button-img');
        if (div.id === elementId) {
            if (div.style.maxHeight) {
                div.style.maxHeight = null;
                imgElement.src = contextPath + "/img/arrow_under.png";
            } else {
                div.style.maxHeight = div.scrollHeight + "px";
                imgElement.src = contextPath + "/img/arrow_upper.png";
            }
        } else {
            div.style.maxHeight = null;
            if(imgElement) { // 혹시 이미지가 없는 경우를 대비
                imgElement.src = contextPath + "/img/arrow_under.png";
            }
        }
    });
}

// 에러 메시지 표출
document.addEventListener("DOMContentLoaded", function() {
    var errorMessage = document.getElementById("message").value;
    console.log(errorMessage)
    if (errorMessage && errorMessage !== '0') {
        alert(errorMessage);
    }
});

// 모임 가입 신청 버튼
function applicationGathering(gatheringId) {
    const contextPath = document.body.getAttribute('data-context-path');
    const url = contextPath + "/gathering/gathering-application";
    $.ajax({
        url: url,
        type: 'POST',
        data: { gatheringId: gatheringId },
        success: function() {
            alert("가입신청이 성공적으로 처리되었습니다.");
            closeCurrentModal();
            location.reload();
        },
        error: function() {
            console.log(url)
            alert("가입신청에 실패했습니다.");
        }
    });
}