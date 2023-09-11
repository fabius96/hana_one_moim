$(document).ready(function(){
    $(".status-change-button").click(function(){
        const memberStatusCode = $(this).data('member-status-code');
        const memberId = $(this).data('member-id');

        $.ajax({
            url: "/community/update-status",
            type: "PATCH",
            data: {
                memberStatusCode: memberStatusCode,
                memberId: memberId
            },
            success: function(response) {
                alert(response.message);
                location.reload();
            },
            error: function() {
                alert("상태 변경에 실패했습니다.");
            }
        });
    });
});