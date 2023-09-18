$(document).ready(function () {
    // community-transaction 페이지 로딩 시 거래 내역 로딩
    const communityAccountNumber = document.body.getAttribute('data-account-number');
    fetchAccountTransactions(communityAccountNumber);
});

function fetchAccountTransactions(accountNumber) {
    const contextPath = document.body.getAttribute('data-context-path');
    const url = contextPath + "/api/community/get-account-transaction";

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

// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 거래내역 출력
function displayTransactions(transactions) {
    const container = $('.transaction-content');

    // 기존 거래 내역 삭제
    container.empty();

    // 거래 내역 추가
    transactions.forEach(transaction => {
        let transactionType = '';
        switch (transaction.transactionTypeCode) {
            case 50:
                transactionType = '입금';
                break;
            case 51:
                transactionType = '출금';
                break;
            case 52:
                transactionType = '체크카드결제';
                break;
            default:
                transactionType = '기타';
        }

        let transactionCategory = '';
        switch (transaction.transactionCategoryCode) {
            case 80:
                transactionCategory = '마트';
                break;
            case 81:
                transactionCategory = '베이커리';
                break;
            case 82:
                transactionCategory = '하나페이맛집';
                break;
            case 83:
                transactionCategory = '대중교통';
                break;
            case 84:
                transactionCategory = '주유/LPG충전';
                break;
            case 85:
                transactionCategory = '커피';
                break;
            case 86:
                transactionCategory = '편의점';
                break;
            case 87:
                transactionCategory = '딜리버리';
                break;
            case 88:
                transactionCategory = '병원/약국';
                break;
            case 89:
                transactionCategory = '온라인식품&쇼핑';
                break;
            default:
                transactionCategory = '기타';
        }

        const transactionRow = `
            <tr class="transaction-item">
                <td>${transaction.transactionTime}</td>
                <td>${transactionType}</td>
                <td>${transactionCategory}</td>
                <td>${transaction.memo}</td>
                <td>${transaction.transactionTypeCode === 50 ? '' : '-' + formatNumberWithCommas(transaction.transactionAmount) + '원'}</td>
                <td>${transaction.transactionTypeCode === 50 ? '+' + formatNumberWithCommas(transaction.transactionAmount) + '원' : ''}</td>
                <td>${formatNumberWithCommas(transaction.balanceAfterTransaction) + '원'}</td>
            </tr>`;
        container.append(transactionRow);
    });
}

