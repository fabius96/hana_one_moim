$(document).ready(function() {
    $(".close-btn, .close").on("click", closeCurrentModal);

    // 색상 드롭다운 값 변경 시 이벤트 리스너
    $('#edit-color').on('change', function() {
        let selectedColor = $(this).val();

        $(this).css('color', selectedColor);
    });
});

function closeCurrentModal() {
    $(this).closest(".modal").hide();
}

// 일정 저장
$('#save-event').on('click', function() {
    const gatheringId = $('body').data('gathering-id');
    const url = `/community/${gatheringId}/calendar`;
    const eventTitle = $('#edit-title').val();
    const eventStartDate = $('#edit-start').val();
    const eventEndDate = $('#edit-end').val();
    const alldayYn = $('#edit-allDay').is(':checked');
    const eventDescription = $('#edit-desc').val();
    const eventColor = $('#edit-color').val();
    const eventData = {
        eventTitle: eventTitle,
        eventStartDate: eventStartDate,
        eventEndDate: eventEndDate,
        alldayYn: alldayYn,
        eventDescription: eventDescription,
        gatheringId : gatheringId,
        eventColor: eventColor
    };

    $.ajax({
        url: url,
        method: 'POST',
        data: JSON.stringify(eventData),
        contentType: 'application/json',
        success: function(response) {
            alert(response.message);
            $('#eventModal').hide();

            location.reload();
        },
        error: function() {
            alert('일정 등록에 실패하였습니다.');
        }
    });
});

// 일정 삭제
$('#delete-event').on('click', function() {
    const eventId = $('#hiddenEventId').val();
    const gatheringId = $('body').data('gathering-id');
    const url = `/community/${gatheringId}/calendar?eventId=${eventId}`;

    $.ajax({
        url: url,
        method: 'DELETE',
        success: function(response) {
            alert(response.message);
            $('#eventModal').hide();

            location.reload();
        },
        error: function() {
            alert('일정 삭제에 실패하였습니다.');
        }
    });
});

// 일정 수정
$('#update-event').on('click', function() {
    const eventId = $('#hiddenEventId').val();
    const gatheringId = $('body').data('gathering-id');
    const url = `/community/${gatheringId}/calendar`;
    const eventTitle = $('#edit-title').val();
    const eventStartDate = $('#edit-start').val();
    const eventEndDate = $('#edit-end').val();
    const alldayYn = $('#edit-allDay').is(':checked');
    const eventDescription = $('#edit-desc').val();
    const eventColor = $('#edit-color').val();
    const eventData = {
        eventId: eventId,
        eventTitle: eventTitle,
        eventStartDate: eventStartDate,
        eventEndDate: eventEndDate,
        alldayYn: alldayYn,
        eventDescription: eventDescription,
        eventColor: eventColor
    };

    if (!eventId) {
        alert('이벤트 ID가 없습니다.');
        return;
    }

    $.ajax({
        url: url,
        method: 'PUT',
        data: JSON.stringify(eventData),
        contentType: 'application/json',
        success: function(response) {
            alert(response.message);
            $('#eventModal').hide();

            location.reload();
        },
        error: function() {
            alert('일정 수정에 실패하였습니다.');
        }
    });
});
