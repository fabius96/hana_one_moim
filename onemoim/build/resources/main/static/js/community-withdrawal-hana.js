// 로그인한 회원의 간편비밀번호(오픈뱅킹 비밀번호 6자리)
var accountPassword = document.getElementById('accountPassword').value;

// 드롭다운
$(document).ready(function () {
    // 초기 설정: 선택된 계좌 값이 있다면 해당 값을 설정
    if ($('.selected-account').length) {
        updateDropdownValue($('.selected-account'), 'account');
    }

    // 드롭다운 클릭 시 동작: 메뉴를 토글하고 화살표 방향을 변경
    $('body').on('click', '.dropdown-container', function () {
        const contextPath = document.body.getAttribute('data-context-path');
        const arrowImg = $(this).find('.dropdown-arrow');
        $(this).find('.dropdown-menu').toggle();

        if ($(this).find('.dropdown-menu').is(':visible')) {
            arrowImg.attr('src', contextPath + '/img/arrow_upper.png');
        } else {
            arrowImg.attr('src', contextPath + '/img/arrow_under.png');
        }
    });

    // 드롭다운 항목 클릭 시 동작: 드롭다운 타입을 확인하고 값을 업데이트
    $('body').on('click', '.dropdown-item', function () {
        // controller 동작을 위한 hidden input value update
        var selectedAccount = $(this).find('.account-number').text();
        $('#selectedAccountNumber').val(selectedAccount);

        const dropdownType = $(this).closest('.dropdown-container').data('dropdown');
        updateDropdownValue(this, dropdownType);
    });

    // form submit 시 검증 및 계좌번호 format 원복
    $("form").on("submit", function (event) {
        var formattedAccountNumber = $("#selectedAccountNumber").val().replaceAll(/[ -]/g, "");
        $("#selectedAccountNumber").val(formattedAccountNumber);
        // 사용자 입력 비밀번호
        var memberInputPassword = $("input[name='accountPassword']").val();

        // amount 필드의 값 검증
        var amountValue = $("input[name='amount']").val();
        if (!amountValue || isNaN(amountValue)) {
            alert("올바른 입금금액을 입력해주세요.");
            $("input[name='amount']").val('').focus(); // clear the input and set focus
            event.preventDefault(); // stop form submission
            return;
        }

        // otherAccountNumber 필드의 값 검증
        var otherAccountNumberValue = $("input[name='otherAccountNumber']").val();
        if (!otherAccountNumberValue) {
            alert("계좌번호를 입력해주세요.");
            $("input[name='otherAccountNumber']").val('').focus();
            event.preventDefault();
            return;
        }

        if (memberInputPassword !== loggedInMemberPassword) {
            console.log(memberInputPassword);
            console.log(loggedInMemberPassword);
            alert("비밀번호가 일치하지 않습니다."); // 오류 메시지
            $("input[name='accountPassword']").val('').focus(); // 비밀번호 input 필드 초기화 및 포커스 이동
            event.preventDefault(); // form 제출 중지
        }
    });

});

// 드롭다운 값을 업데이트하는 함수
function updateDropdownValue(element, dropdownType) {
    let parentDropdown = $(element).closest('.dropdown-container');

    // 계좌 타입의 드롭다운일 경우
    if (dropdownType === "account") {
        const selectedBalance = $(element).data('balance');
        $('.money').text(formatNumberWithCommas(selectedBalance) + '원');
        let accountNumber = $(element).find('.account-number').text().trim();
        parentDropdown.find('.dropdown-title').text(accountNumber);
    }
    // 은행 타입의 드롭다운일 경우
    else if (dropdownType === "bank") {
        let bankName = $(element).text().trim();
        parentDropdown.find('.dropdown-title').text(bankName);
    }
}

// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}