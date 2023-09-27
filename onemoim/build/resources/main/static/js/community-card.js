let currentMonth = new Date().getMonth() + 1;
let myDoughnutChart;

$(document).ready(function () {
    const gatheringId = window.location.pathname.split("/")[2];
    getTransactionData(gatheringId, currentMonth);
    fetchAccountTransactions(gatheringId, currentMonth);
});

// 이벤트 위임을 사용한 화살표 클릭 이벤트
$(document).on('click', ".arrow-button-img", function () {
    if ($(this).attr("alt") === "좌측화살표") {
        currentMonth--;
        if (currentMonth < 1) {
            currentMonth = 12;
        }
    } else {
        currentMonth++;
        if (currentMonth > 12) {
            currentMonth = 1;
        }
    }

    const gatheringId = window.location.pathname.split("/")[2];
    getTransactionData(gatheringId, currentMonth);
    fetchAccountTransactions(gatheringId, currentMonth);
});

// 서버에서 거래 데이터 가져오는 함수
function getTransactionData(gatheringId, month) {
    $.ajax({
        url: `/community/${gatheringId}/card-transaction?month=${month}`,
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            drawChart(data); // 차트 그리기

            // 지출 총액 업데이트
            let totalAmount = 0;
            for (let key in data) {
                if (data.hasOwnProperty(key)) {
                    totalAmount += data[key];
                }
            }
            $(".chart-total").text(formatNumberWithCommas(totalAmount) + "원");

            $(".text-span").text(`2023년 ${month}월 지출분석`);
            $(".chart-name").text(`2023년 ${month}월 지출 총액`);
            $(".transaction-text-span").text(`2023년 ${month}월 지출 TOP 5`);
        },
        error: function (error) {
            console.error("데이터 오류:", error);
        }
    });
}

// 반환된 데이터로 차트와 범례 그리는 함수
function drawChart(data) {
    // 전달된 데이터를 transactions 배열로 변환
    var transactions = Object.keys(data).map(function (key) {
        return {category: key, amount: data[key]};
    });

    // 범례 생성 전 기존 범례 삭제
    $("#legend-table tbody").empty();

    // 카테고리별 금액 합산
    var aggregatedData = {};
    transactions.forEach(function (transaction) {
        if (!aggregatedData[transaction.category]) {
            aggregatedData[transaction.category] = 0;
        }
        aggregatedData[transaction.category] += transaction.amount;
    });

    // 합산 데이터에서 카테고리와 금액 배열로 분리
    var categories = Object.keys(aggregatedData);
    var amounts = categories.map(category => aggregatedData[category]);
    var total = amounts.reduce((acc, curr) => acc + curr);

    // 범례 레이블 생성
    var legendLabels = categories.map((category, index) => {
        var value = amounts[index];
        var percentage = Math.round(value / total * 100);
        return `${category}`;
    });

    if (myDoughnutChart) {
        myDoughnutChart.destroy(); // 기존 차트 제거
    }

    // 차트 생성
    var ctx = document.getElementById('myDoughnutChart').getContext('2d');
    myDoughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: legendLabels,
            datasets: [{
                data: amounts,
                backgroundColor: ['#FF89C2', '#FFA0D0', '#FFB08B', '#FFCCA0', '#FFE570', '#8BFFD6', '#8BDFFF', '#8BAFFF', '#8E8BFF', '#A8A0FF'],
            }]
        },
        // 차트 옵션 설정
        options: {
            responsive: true,
            maintainAspectRatio: true,
            aspectRatio: 1.2,
            cutout: '60%',
            plugins: {
                legend: {
                    display: false,
                    position: 'right'
                }
            },
            layout: {
                padding: {
                    right: 30
                }
            }
        }
    });

    // 범례 생성
    var legendTable = document.getElementById('legend-table').getElementsByTagName('tbody')[0];
    categories.forEach((category, index) => {
        var row = legendTable.insertRow(-1);
        var colorCell = row.insertCell(0);
        var labelCell = row.insertCell(1);
        var valueCell = row.insertCell(2);
        var percentageCell = row.insertCell(3);

        var value = amounts[index];
        var percentage = Math.round(value / total * 100);

        colorCell.innerHTML = `<div class="legend-color" style="background-color: ${myDoughnutChart.data.datasets[0].backgroundColor[index]}"></div>`;
        labelCell.textContent = legendLabels[index];
        valueCell.textContent = formatNumberWithCommas(value) + '원';
        percentageCell.textContent = percentage + "%";
    });
}

function fetchAccountTransactions(gatheringId, month) {
    const url = `/api/community/${gatheringId}/get-account-transaction-top5?month=${month}`;

    $.ajax({
        type: "GET",
        url: url,
        success: function (transactions) {
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
            </tr>`;
        container.append(transactionRow);
    });
}
