$(document).ready(function () {
    // 초기 설정
    initializeDropdown();

    // 드롭다운 클릭 이벤트 연결
    bindDropdownEvents();
});

// 초기 설정: 선택된 계좌 값이 있다면 해당 값을 설정
function initializeDropdown() {
    if ($('.selected-account').length) {
        updateDropdownValue($('.selected-account'), 'account');
    }
}

// 드롭다운 클릭 이벤트 연결
function bindDropdownEvents() {
    $('body').on('click', '.dropdown-container', handleDropdownClick);
    $('body').on('click', '.dropdown-item', handleDropdownItemClick);
}

// 드롭다운 클릭 이벤트 핸들러
function handleDropdownClick() {
    const contextPath = document.body.getAttribute('data-context-path');
    const arrowImg = $(this).find('.dropdown-arrow');
    $(this).find('.dropdown-menu').toggle();

    if ($(this).find('.dropdown-menu').is(':visible')) {
        arrowImg.attr('src', contextPath + '/img/arrow_upper.png');
    } else {
        arrowImg.attr('src', contextPath + '/img/arrow_under.png');
    }
}

// 드롭다운 항목 클릭 이벤트 핸들러
function handleDropdownItemClick() {
    // controller 동작을 위한 hidden input value update
    const rawAccountNumber = $(this).find('.account-number').text().trim();
    const selectedAccountNumber = cleanAccountNumber(rawAccountNumber);
    $('#selectedAccountNumber').val(selectedAccountNumber);


    const dropdownType = $(this).closest('.dropdown-container').data('dropdown');
    updateDropdownValue(this, dropdownType);

    // 선택된 계좌 번호로 거래 내역 가져오기
    fetchAccountTransactions(selectedAccountNumber);
}

// 드롭다운 값을 업데이트하는 함수
function updateDropdownValue(element, dropdownType) {
    const parentDropdown = $(element).closest('.dropdown-container');

    if (dropdownType === "account") {
        const selectedBalance = $(element).data('balance');
        $('.money').text(formatNumberWithCommas(selectedBalance) + '원');
        const accountNumber = $(element).find('.account-number').text().trim();
        parentDropdown.find('.dropdown-title').text(accountNumber);
    }
}

function fetchAccountTransactions(accountNumber) {
    const contextPath = document.body.getAttribute('data-context-path');
    const url = contextPath + "/api/account/get-account-transaction";

    $.ajax({
        type: "GET",
        url: url,
        data: {accountNumber: accountNumber},
        success: function (transactions) {
            // 결과를 화면에 표시하는 함수
            displayTransactions(transactions);
        }
    });
}

// 계좌번호 포맷 함수
function cleanAccountNumber(accountNumber) {
    return accountNumber.replace(/[\s-]/g, '');
}


// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 계좌번호 포맷 생성 및 적용
document.addEventListener('DOMContentLoaded', function () {
    let accountNumbers = document.querySelectorAll('.account-number');
    accountNumbers.forEach(element => {
        let formattedNumber = formatAccountNumber(element.textContent.trim());
        element.textContent = formattedNumber;
    });
});

// 계좌번호 format
function formatAccountNumber(accountNumber) {
    if (accountNumber.length !== 14) {
        return accountNumber;
    }
    return accountNumber.substring(0, 3) + "  -  " +
        accountNumber.substring(3, 9) + "  -  " +
        accountNumber.substring(9);
}

// 거래내역 출력
function displayTransactions(transactions) {
    const container = $('.transaction-content');

    // 기존 거래 내역 삭제
    container.empty();

    // 거래 내역 추가
    transactions.forEach(transaction => {
        const transactionRow = `
            <tr class="transaction-item">
                <td>${transaction.transactionTime}</td>
                <td>${transaction.transactionTypeCode === 50 ? '입금' : '출금'}</td>
                <td>${transaction.memo}</td>
                <td>${transaction.transactionTypeCode === 50 ? '' : '-' + formatNumberWithCommas(transaction.transactionAmount) + '원'}</td>
                <td>${transaction.transactionTypeCode === 50 ? '+' + formatNumberWithCommas(transaction.transactionAmount) + '원' : ''}</td>
                <td>${formatNumberWithCommas(transaction.balanceAfterTransaction) + '원'}</td>
            </tr>`;
        container.append(transactionRow);
    });
}

