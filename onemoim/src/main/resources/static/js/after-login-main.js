// 모든 .container 요소 선택
let containers = document.querySelectorAll('.container');
// 현재 활성화된 슬라이드 인덱스 초기화
let currentContainer = 0;

// 슬라이드 전환 함수
function changeContainer() {
    // 모든 컨테이너의 opacity 0으로 설정
    for (let container of containers) {
        container.style.opacity = 0;
    }

    // 현재 컨테이너 인덱스 1 증가
    currentContainer++;

    // 모든 슬라이드 다 보여주면 처음 슬라이드로 돌아가기
    if (currentContainer === containers.length) {
        currentContainer = 0;
    }

    // 현재 활성화된 슬라이드 opacity 1로 설정
    containers[currentContainer].style.opacity = 1;
}

// 3초마다 changeContainer 함수 실행
setInterval(changeContainer, 4000);