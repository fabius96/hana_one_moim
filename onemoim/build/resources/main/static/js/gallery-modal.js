$(document).ready(function () {
    $(".close-btn, .close").on("click", closeCurrentModal);

    $('#write-button').on('click', function () {
        $('#modal-electronic-transactions').show();
    });

    $('#galleryImage').on('change', function () {
        const files = $(this)[0].files;
        if (files.length > 1) {
            $('#file-name').text(files.length + "개의 파일 선택됨");
        } else if (files.length === 1) {
            $('#file-name').text(files[0].name);
        } else {
            $('#file-name').text("이미지 업로드");
        }
    });

    $('form').on('submit', function (e) {
        e.preventDefault();

        var formData = new FormData(this);

        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                // alert(response.message);
                $('#modal-electronic-transactions').hide();
                location.reload();
            },
            error: function () {
                alert('게시글 등록 중 오류가 발생했습니다.');
            }
        });
    });
});

function closeCurrentModal() {
    $(this).closest(".modal").hide();
}