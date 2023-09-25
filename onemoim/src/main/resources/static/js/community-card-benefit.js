$(document).ready(function () {
    // 모든 슬라이더 선택
    let sliders = document.querySelectorAll(".slider");

    // 모든 슬라이더의 값을 0으로 초기화
    sliders.forEach(slider => {
        slider.value = 0;
        let hiddenInput = slider.previousElementSibling;
        hiddenInput.value = 0;
        slider.dataset.initialValue = '0'; // 초기 값을 data 속성에 설정
        slider.dataset.changed = 'false'; // 변경 여부를 저장하는 data 속성
    });

    // 모임 ID 값을 가져옴
    let gatheringId = $("#gatheringId").val();

    // 서버에서 카드 혜택 데이터 로드
    $.get(`/community/${gatheringId}/card-benefit/data`, function (cardBenefits) {
        sliders.forEach(slider => {
            // 각 슬라이더의 혜택 이름 찾기
            let benefitTitle = slider.closest(".benefit-box").querySelector(".benefit-title").textContent;

            // 카드 혜택 데이터에서 해당 혜택 정보 찾기
            let benefitInfo = cardBenefits.find(benefit => benefit.benefitName === benefitTitle);

            // 혜택 상자와 슬라이더 값 요소, 숨겨진 입력 요소 선택
            let benefitBox = slider.closest(".benefit-box");
            let sliderValue = slider.nextElementSibling.querySelector("span");
            let hiddenInput = slider.previousElementSibling;

            // 혜택 정보가 있으면 초기 값을 설정
            if (benefitInfo) {
                slider.value = benefitInfo.benefitRate;
                hiddenInput.value = slider.value;
                slider.dataset.initialValue = benefitInfo.benefitRate.toString(); // 초기 값을 문자열로 변환

                sliderValue.textContent = slider.value;
                // 초기 값에 따라 테두리 설정
                setBorder(slider.value, benefitBox);

                // 슬라이더 진행률 계산 및 배경색 변경
                let progress = (slider.value - slider.min) / (slider.max - slider.min) * 100;
                let color = `linear-gradient(90deg, #7DEDF3 ${progress}%, #ddd ${progress}%)`;
                slider.style.background = color;
            }

            // 슬라이더 값 변경 이벤트 핸들러 설정
            slider.addEventListener("input", function () {
                slider.dataset.changed = 'true'; // 사용자에 의해 변경됨
                // 슬라이더와 숨겨진 입력 값 변경
                sliderValue.textContent = slider.value;
                hiddenInput.value = slider.value;

                // 슬라이더 진행률 계산 및 배경색 변경
                let progress = (slider.value - slider.min) / (slider.max - slider.min) * 100;
                let color = `linear-gradient(90deg, #7DEDF3 ${progress}%, #ddd ${progress}%)`;
                slider.style.background = color;

                // 변경된 값에 따라 테두리 설정
                setBorder(slider.value, benefitBox);
            });
        });

        // 폼 제출 이벤트 핸들러 설정
        $("form").submit(function (event) {
            event.preventDefault();

            let total = 0;

            // 모든 슬라이더 값 합계 계산
            sliders.forEach(slider => {
                // 값이 변경되지 않았다면 초기 값을 사용
                let value = (slider.dataset.changed === 'true') ? parseInt(slider.value) : parseInt(slider.dataset.initialValue);
                total += value;
            });

            // 합계가 10이 아니면 제출 중단
            if (total !== 10) {
                alert("선택 영역 할인율의 총합이 10%가 되도록 설정해주세요.");
            } else {
                var formData = $(this).serialize();
                var actionUrl = $(this).attr('action');

                $.ajax({
                    type: 'POST',
                    url: actionUrl,
                    data: formData,
                    success: function (response) {
                        window.location.href = `/community/${gatheringId}/edit-benefit-ok`;
                        console.log(response);
                    },
                    error: function (error) {
                        // 에러 발생 시 작업
                    }
                });
            }
        });
    });

    // 테두리 설정 함수
    function setBorder(value, element) {
        // 값이 1 이상이면 테두리 설정, 아니면 테두리 제거
        if (value >= 1) {
            element.style.border = "0.5px solid #7DEDF3";
        } else {
            element.style.borderColor = "transparent";
        }
    }
});
