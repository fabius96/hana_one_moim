$(document).ready(function() {
    $('#gatheringImage').on('change', function() {
        const fileName = $(this).val().split('\\').pop();
        if(fileName) {
            $('#file-name').text(fileName);
        } else {
            $('#file-name').text("모임 대표 이미지 업로드");
        }
    });
});
