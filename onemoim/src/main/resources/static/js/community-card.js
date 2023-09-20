var transactions = [
    {category: "마트", amount: 5000},
    {category: "베이커리", amount: 2000},
    {category: "하나페이 맛집", amount: 1500},
    {category: "대중교통", amount: 8000},
    {category: "주유/LPG 충전", amount: 8000},
    {category: "커피", amount: 8000},
    {category: "편의점", amount: 8000},
    {category: "딜리버리", amount: 8000},
    {category: "병원/약국", amount: 8000},
    {category: "온라인식품&쇼핑", amount: 8000},
];

var aggregatedData = {};

transactions.forEach(function (transaction) {
    if (!aggregatedData[transaction.category]) {
        aggregatedData[transaction.category] = 0;
    }
    aggregatedData[transaction.category] += transaction.amount;
});

var categories = Object.keys(aggregatedData);
var amounts = categories.map(category => aggregatedData[category]);
var total = amounts.reduce((acc, curr) => acc + curr);

// 범례에 표시될 레이블을 동적으로 생성
var legendLabels = categories.map((category, index) => {
    var value = amounts[index];
    var percentage = Math.round(value / total * 100);
    return `${category}`;
});

var ctx = document.getElementById('myDoughnutChart').getContext('2d');
var myDoughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: legendLabels,
        datasets: [{
            data: amounts,
            backgroundColor: ['#FF89C2', '#FFA0D0', '#FFB08B', '#FFCCA0', '#FFE570', '#8BFFD6', '#8BDFFF', '#8BAFFF', '#8E8BFF', '#A8A0FF'],
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: true,
        aspectRatio: 1.2,  // 이 값을 조절하여 차트의 크기를 변경하세요.
        cutout: '60%',
        plugins: {
            legend: {
                display: false,
                position: 'right'
            }
        },
        layout: {
            padding: {
                right: 30  // 차트와 범례 사이의 간격 조절
            }
        }
    }
});

// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 범례를 HTML 테이블 형식으로 생성
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
