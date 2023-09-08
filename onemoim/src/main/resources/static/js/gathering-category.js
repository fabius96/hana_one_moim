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


// 모임 생성 페이지로 이동
function createGathering() {
    location.href = '/gathering/gathering-create';
}

// 주제별 모임 조회
function showGatheringCategory(interest) {
    location.href = '/gathering/gathering-category?interest=' + interest;
}

let currentSlide = 0;
const slider = document.querySelector('.recommend-slider');
const items = document.querySelectorAll('.recommend-item');
const totalSlides = 3;

document.getElementById('nextBtn').addEventListener('click', function() {
    if (currentSlide < totalSlides - 1) { // 마지막 슬라이드가 아니면
        currentSlide++;
    } else { // 마지막 슬라이드면
        currentSlide = 0; // 첫 번째 슬라이드로 돌아가기
    }
    updateSliderPosition();
});

document.getElementById('prevBtn').addEventListener('click', function() {
    if (currentSlide > 0) { // 첫 번째 슬라이드가 아니면
        currentSlide--;
        updateSliderPosition();
    }
});

function updateSliderPosition() {
    const offset = -currentSlide * 55; // 현재 슬라이드 위치를 퍼센트로 계산
    slider.style.transform = `translateX(${offset}%)`;
}

document.addEventListener("DOMContentLoaded", function() {
    var errorMessage = document.getElementById("message").value;
    console.log(errorMessage)
    if (errorMessage && errorMessage !== '0') {
        alert(errorMessage);
    }
});