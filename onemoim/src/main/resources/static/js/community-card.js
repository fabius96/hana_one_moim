// 페이지 로드 시 실행
$(document).ready(function() {
    // 현재 URL에서 gatheringId 추출
    const gatheringId = window.location.pathname.split("/")[2];

    // AJAX 요청으로 서버에서 카드 소비 데이터 받아옴
    $.ajax({
        url: `/community/${gatheringId}/card-transaction`,
        method: 'GET',
        dataType: 'json',
        success: function(data) {
            // 데이터 받아오면 차트 그리는 함수 호출
            drawChart(data);
        },
        error: function(error) {
            console.error("Error fetching data:", error);
        }
    });
});

// 반환된 데이터로 차트와 범례 그리는 함수
function drawChart(data) {
    // 전달된 데이터를 transactions 배열로 변환
    var transactions = Object.keys(data).map(function(key) {
        return { category: key, amount: data[key] };
    });

    // 카테고리별로 금액 합산
    var aggregatedData = {};
    transactions.forEach(function (transaction) {
        if (!aggregatedData[transaction.category]) {
            aggregatedData[transaction.category] = 0;
        }
        aggregatedData[transaction.category] += transaction.amount;
    });

    // 금액 합산 데이터에서 카테고리와 금액 배열로 분리
    var categories = Object.keys(aggregatedData);
    var amounts = categories.map(category => aggregatedData[category]);
    var total = amounts.reduce((acc, curr) => acc + curr);

    // 범례에 표시될 레이블 생성
    var legendLabels = categories.map((category, index) => {
        var value = amounts[index];
        var percentage = Math.round(value / total * 100);
        return `${category}`;
    });

    // 차트 생성
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

    // 숫자 포맷 함수
    function formatNumberWithCommas(number) {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

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
