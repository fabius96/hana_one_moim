let calendar; // 전역 캘린더 인스턴스

const calendarEl = document.getElementById("calendar");

// 페이지 로드 시 캘린더 렌더링
document.addEventListener('DOMContentLoaded', function () {
    const rawData = $('body').data('events-json');
    const convertedData = convertEventData(rawData);
    calendar_rendering(convertedData);
});

// 데이터 변환
function convertEventData(eventsData) {
    return eventsData.map(event => ({
        id: event.eventId,
        title: event.eventTitle,
        start: event.eventStartDate.replace(" ", "T"),
        end: event.eventEndDate.replace(" ", "T"),
        allDay: event.alldayYn,
        description: event.eventDescription,
        color: event.eventColor
    }));
}

// 캘린더 렌더링
function calendar_rendering(eventsData) {
    calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: "dayGridMonth",
        firstDay: 1,
        titleFormat: function (date) {
            const year = date.date.year;
            const month = date.date.month + 1;
            return year + "년 " + month + "월";
        },
        slotLabelFormat: {
            hour: '2-digit',
            minute: '2-digit',
            omitZeroMinute: false,
            hour12: false
        },
        eventTimeFormat: {
            hour: '2-digit',
            minute: '2-digit',
            omitZeroMinute: false,
            hour12: false
        },
        buttonText: {
            today: '오늘'
        },
        selectable: true,
        events: eventsData,
        select: function (info) {
            const selectedDate = new Date(info.startStr);
            const currentTime = new Date();

            selectedDate.setHours(currentTime.getHours(), currentTime.getMinutes(), currentTime.getSeconds());

            document.getElementById('edit-start').value = formatDateTime(selectedDate);

            const endDate = new Date(selectedDate);
            endDate.setHours(endDate.getHours() + 1);
            document.getElementById('edit-end').value = formatDateTime(endDate);

            document.getElementById('edit-title').value = "";
            document.getElementById('edit-allDay').checked = false;
            document.getElementById('edit-desc').value = "";
            document.getElementById('edit-color').value = "";

            document.getElementById('save-event').style.display = "block";
            document.getElementById('update-event').style.display = "none";
            document.getElementById('delete-event').style.display = "none";

            openModal();
        },
        eventClick: function (info) {
            showEventDetails(info.event);
        }
    });

    calendar.render();

}

// 이벤트 클릭 시 상세 보기
function showEventDetails(event) {
    document.getElementById('edit-title').value = event.title;
    document.getElementById('edit-start').value = formatDateTime(new Date(event.start));
    document.getElementById('edit-end').value = formatDateTime(new Date(event.end));
    document.getElementById('edit-allDay').checked = event.allDay;
    document.getElementById('edit-desc').value = event.extendedProps.description;
    document.getElementById('edit-color').value = event.backgroundColor || "#008375";

    document.getElementById('hiddenEventId').value = event.id;

    document.getElementById('save-event').style.display = "none";
    document.getElementById('update-event').style.display = "block";
    document.getElementById('delete-event').style.display = "block";

    openModal();
}

// 날짜와 시간 포맷 변환
function formatDateTime(date) {
    let year = date.getFullYear();
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');
    let hours = date.getHours().toString().padStart(2, '0');
    let minutes = date.getMinutes().toString().padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}`;
}

// 모달 열기
function openModal() {
    document.getElementById("eventModal").style.display = "block";
    const currentDate = new Date();
    const formattedDate = formatDateTime(currentDate);

    const endDate = new Date(currentDate);
    endDate.setHours(currentDate.getHours() + 1);
    const formattedEndDate = formatDateTime(endDate);

    if (!document.getElementById('edit-start').value) {
        document.getElementById('edit-start').value = formattedDate;
    }
    if (!document.getElementById('edit-end').value) {
        document.getElementById('edit-end').value = formattedEndDate;
    }
}