// 페이지 로드 완료 시 실행
$(document).ready(function () {
    // 서버에서 받은 accountDtoList
    const accountDtoListStr = document.body.getAttribute('data-account');

    // 문자열을 JSON 객체로 변환
    const accountDtoList = JSON.parse(accountDtoListStr);
    accountDtoList.forEach(accountDto => {
        const bankName = getBankName(accountDto.bankCode);
        const accountRow = `
            <tr class="account-item">
                <td>${accountDto.accountNickname}</td>
                <td>${bankName}</td> 
                <td class="account-number" data-raw-number="${accountDto.accountNumber}">
                    ${formatAccountNumber(accountDto.accountNumber)}
                </td>
                <td class="money">${formatNumberWithCommas(accountDto.balance)}원</td>
                </td>
                 <td><input type="checkbox" ${accountDto.openbankingRegistered === 'Y' ? 'checked disabled' : ''} class="account-info-checkbox"/></td>
            </tr>`;
        $('.account-content').append(accountRow);
    });

    // 연결해제 버튼 클릭 이벤트
    $('.registration-button').click(async function () {
        const checkboxes = $('.account-info-checkbox:checked');
        const promises = [];

        checkboxes.each(function () {
            const accountNumber = $(this).closest('tr').find('.account-number').data('raw-number');
            const promise = $.ajax({
                url: 'http://localhost:8081/openbanking/registration-account?accountNumber=' + accountNumber,
                type: 'PUT'
            });
            promises.push(promise);
        });

        try {
            await Promise.all(promises);
            alert('계좌 연결이 성공적으로 완료되었습니다.');
        } catch (error) {
            alert('계좌 연결에 실패했습니다.');
        } finally {
            location.reload();
        }
    });
});

// bankCode를 기반으로 은행 이름을 반환하는 함수
function getBankName(bankCode) {
    switch (bankCode) {
        case 20:
            return '하나은행';
        case 21:
            return '신한은행';
        case 22:
            return '국민은행';
        case 23:
            return '우리은행';
        default:
            return '알 수 없는 은행';
    }
}

// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 계좌번호 format 함수
function formatAccountNumber(accountNumber) {
    if (accountNumber.length !== 14) {
        return accountNumber;
    }
    return accountNumber.substring(0, 3) + " - " +
        accountNumber.substring(3, 9) + " - " +
        accountNumber.substring(9);
}
