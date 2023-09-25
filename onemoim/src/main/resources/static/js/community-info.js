$(document).ready(function(){
    $(".status-change-button").click(function(){
        const memberStatusCode = $(this).data('member-status-code');
        const memberId = $(this).data('member-id');
        const gatheringId = $('body').data('gathering-id');

        $.ajax({
            url: "/community/update-status",
            type: "PATCH",
            data: {
                memberStatusCode: memberStatusCode,
                memberId: memberId,
                gatheringId: gatheringId
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

Kakao.Share.createDefaultButton({
    container: '#kakaotalk-sharing-btn',
    objectType: 'text',
    text:
        '기본 템플릿으로 제공되는 텍스트 템플릿은 텍스트를 최대 200자까지 표시할 수 있습니다. 텍스트 템플릿은 텍스트 영역과 하나의 기본 버튼을 가집니다. 임의의 버튼을 설정할 수도 있습니다. 여러 장의 이미지, 프로필 정보 등 보다 확장된 형태의 카카오톡 공유는 다른 템플릿을 이용해 보낼 수 있습니다.',
    link: {
        mobileWebUrl: 'http://localhost:8080/',
        webUrl: 'http://localhost:8080/',
    },
});