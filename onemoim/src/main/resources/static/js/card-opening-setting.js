$(document).ready(function () {
    // 모든 슬라이더 선택
    let sliders = document.querySelectorAll(".slider");

    sliders.forEach(slider => {
        // 현재 슬라이더의 값을 표시하는 요소 선택
        let sliderValue = slider.nextElementSibling.querySelector("span");
        let benefitBox = slider.closest(".benefit-box");

        // 각 슬라이더에 대한 input 이벤트 리스너 추가
        slider.addEventListener("input", function () {
            // 슬라이더 값 표시
            sliderValue.textContent = slider.value;

            // 슬라이더 진행률 계산
            let progress = (slider.value - slider.min) / (slider.max - slider.min) * 100;

            // 슬라이더 배경색 생성
            let color = `linear-gradient(90deg, #7DEDF3 ${progress}%, #ddd ${progress}%)`;

            // 배경색 적용
            slider.style.background = color;

            // 슬라이더 값이 1 이상이면 .benefit-box에 테두리를 추가하고, 그렇지 않으면 제거
            if (slider.value >= 1) {
                benefitBox.style.border = "0.5px solid #7DEDF3";
            } else {
                benefitBox.style.borderColor = "transparent";
            }

        });
    });

    // 혜택 설정 값 검증
    $("form").submit(function (event) {
        let total = 0;
        sliders.forEach(slider => {
            total += parseInt(slider.value);
        });

        if (total !== 10 ) {
            alert("선택 영역 할인율의 총합이 10%가 되도록 설정해주세요.");
            event.preventDefault();  // form 제출 거절
        }
    });
});
