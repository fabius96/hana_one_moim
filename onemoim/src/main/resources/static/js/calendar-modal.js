$(document).ready(function() {
    $(".close-btn, .close").on("click", closeCurrentModal);
});


function closeCurrentModal() {
    $(this).closest(".modal").hide();
}

$('#save-event').on('click', function() {
    const gatheringId = $('body').data('gathering-id');
    const url = `/community/${gatheringId}/calendar`;
    console.log(url);
    const eventTitle = $('#edit-title').val();
    const eventStartDate = $('#edit-start').val();
    const eventEndDate = $('#edit-end').val();
    const alldayYn = $('#edit-allDay').is(':checked');
    const eventDescription = $('#edit-desc').val();
    const eventData = {
        eventTitle: eventTitle,
        eventStartDate: eventStartDate,
        eventEndDate: eventEndDate,
        alldayYn: alldayYn,
        eventDescription: eventDescription,
        gatheringId : gatheringId
    };

    $.ajax({
        url: url,
        method: 'POST',
        data: JSON.stringify(eventData),
        contentType: 'application/json',
        success: function(response) {
            alert(response.message);
            $('#eventModal').hide();
        },
        error: function() {
            alert('일정 등록에 실패하였습니다.');
        }
    });
});