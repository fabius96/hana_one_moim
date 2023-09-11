// 캘린더 요소 참조
const calendarEl = document.getElementById("calendar");

// 캘린더 렌더링 함수
function calendar_rendering() {
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: "dayGridMonth", // 초기 뷰 설정
        firstDay: 1, // 시작 요일 설정 (월요일)
        titleFormat: function (date) { // 제목 형식 설정
            const year = date.date.year;
            const month = date.date.month + 1;
            return year + "년 " + month + "월";
        },
        selectable: true,
        select: function (info) {
            const selectedDate = new Date(info.startStr); // 사용자가 선택한 날짜
            const currentTime = new Date(); // 현재 시간

            // 선택한 날짜와 현재 시간 합치기
            selectedDate.setHours(currentTime.getHours(), currentTime.getMinutes(), currentTime.getSeconds());

            document.getElementById('edit-start').value = formatDateTime(selectedDate);

            const endDate = new Date(selectedDate);
            endDate.setHours(endDate.getHours() + 1); // 선택한 날짜의 현재 시간 + 1시간
            document.getElementById('edit-end').value = formatDateTime(endDate);

            // 모달 표시
            openModal();
        }
    });

    calendar.render(); // 캘린더 렌더링 실행
}

// 페이지 로드 시 캘린더 렌더링 함수 호출
document.addEventListener('DOMContentLoaded', function () {
    calendar_rendering();
});

// 모달 열기
function openModal() {
    document.getElementById("eventModal").style.display = "block";

    const currentDate = new Date(); // 현재 날짜 및 시간
    console.log(currentDate);
    const formattedDate = formatDateTime(currentDate);

    const endDate = new Date(currentDate);
    endDate.setHours(currentDate.getHours() + 1); // 현재 시간 + 1시간
    const formattedEndDate = formatDateTime(endDate);

    // input 필드에 기본 값을 설정.(이미 값이 존재하는 경우 제외)
    if (!document.getElementById('edit-start').value) {
        document.getElementById('edit-start').value = formattedDate;
    }
    if (!document.getElementById('edit-end').value) {
        document.getElementById('edit-end').value = formattedEndDate;
    }
}

// 날짜와 시간을 'YYYY-MM-DDTHH:MM:SS' 형식으로 변환하는 함수
function formatDateTime(date) {
    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0'); // 월
    let day = date.getDate().toString().padStart(2, '0'); // 일
    let hours = date.getHours().toString().padStart(2, '0'); // 시
    let minutes = date.getMinutes().toString().padStart(2, '0'); // 분

    return `${year}-${month}-${day} ${hours}:${minutes}`;
}
