const firstArea = document.body.getAttribute('data-first-area');
const secondArea = document.body.getAttribute('data-second-area');
const thirdArea = document.body.getAttribute('data-third-area');

$(document).ready(function () {
    // 클릭 이벤트 등록
    $(".recommend-button").on("click", function (event) {
        event.stopPropagation();
        $("#modal-electronic-transactions").show();
    });

    $(".agree-btn").on("click", function () {
        applyRecommendedBenefits();
        closeCurrentModal();
    });

    $(".close-btn, .close").on("click", closeCurrentModal);
});

function applyRecommendedBenefits() {

    let firstRate = 5;
    let secondRate = 3;
    let thirdRate = 2;

    let areas = [firstArea, secondArea, thirdArea];
    let rates = [firstRate, secondRate, thirdRate];

    let sliders = document.querySelectorAll(".slider");

    sliders.forEach(slider => {
        let benefitTitle = slider.closest(".benefit-box").querySelector(".benefit-title").textContent;
        let index = areas.indexOf(benefitTitle);
        let rate = index !== -1 ? rates[index] : 0;

        // 혜택 상자와 슬라이더 값 요소, 숨겨진 입력 요소 선택
        let benefitBox = slider.closest(".benefit-box");

        slider.value = rate;
        let hiddenInput = slider.previousElementSibling;
        hiddenInput.value = rate;
        let sliderValue = slider.nextElementSibling.querySelector("span");
        sliderValue.textContent = slider.value;


        // 초기 값에 따라 테두리 설정
        setBorder(slider.value, benefitBox);

        // 슬라이더 진행률 계산 및 배경색 변경
        let progress = (slider.value - slider.min) / (slider.max - slider.min) * 100;
        let color = `linear-gradient(90deg, #7DEDF3 ${progress}%, #ddd ${progress}%)`;
        slider.style.background = color;
    });
}

function closeCurrentModal() {
    $("#modal-electronic-transactions").hide();
}

// 테두리 설정 함수
function setBorder(value, element) {
    // 값이 1 이상이면 테두리 설정, 아니면 테두리 제거
    if (value >= 1) {
        element.style.border = "0.5px solid #7DEDF3";
    } else {
        element.style.borderColor = "transparent";
    }
}
