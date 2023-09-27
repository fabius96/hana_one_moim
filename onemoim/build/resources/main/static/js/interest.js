$(document).ready(function() {
    // 체크박스 선택을 확인하는 이벤트
    $('input[type="checkbox"]').on('change', function() {
        if ($('input[type="checkbox"]:checked').length > 3) {
            alert('최대 3개까지만 선택 가능합니다.');
            $(this).prop('checked', false); // 체크 해제
        }
    });

    // 폼 제출을 확인하는 이벤트
    $('#interest-form').on('submit', function(e) {
        var checkedCount = $('input[type="checkbox"]:checked').length;

        if (checkedCount > 3) {
            alert('최대 3개까지만 선택 가능합니다.');
            e.preventDefault(); // 폼 제출 방지
        } else if (checkedCount === 0) {
            alert('최소 1개 이상의 영역을 선택해주세요.');
            e.preventDefault(); // 폼 제출 방지
        }
    });
});
