// 아코디언
function toggleCustomAccordion(elementId) {
    const contextPath = document.body.getAttribute('data-context-path');

    let allContentDivs = document.querySelectorAll('.accordion-collapse');
    allContentDivs.forEach(div => {
        let imgElement = div.previousElementSibling.querySelector('.button-img');
        if (div.id === elementId) {
            if (div.style.maxHeight) {
                div.style.maxHeight = null;
                imgElement.src = contextPath + "/img/arrow_under.png";
            } else {
                div.style.maxHeight = div.scrollHeight + "px";
                imgElement.src = contextPath + "/img/arrow_upper.png";
            }
        } else {
            div.style.maxHeight = null;
            if(imgElement) { // 혹시 이미지가 없는 경우를 대비
                imgElement.src = contextPath + "/img/arrow_under.png";
            }
        }
    });
}


// 계좌번호 포맷 생성 및 적용
document.addEventListener('DOMContentLoaded', function () {
    let accountNumbers = document.querySelectorAll('.account-number');
    accountNumbers.forEach(element => {
        let formattedNumber = formatAccountNumber(element.textContent.trim());
        element.textContent = formattedNumber;
    });
});
function formatAccountNumber(accountNumber) {
    if (accountNumber.length !== 14) {
        return accountNumber;
    }
    return accountNumber.substring(0, 3) + "  -  " +
        accountNumber.substring(3, 9) + "  -  " +
        accountNumber.substring(9);
}

// DOM 숫자 포맷 적용
$(document).ready(function() {
    // .money 엘리먼트들을 순회
    $(".money").each(function() {
        // 현재 엘리먼트의 텍스트 값을 가져와 숫자만 추출
        let currentBalance = $(this).text().replace(/[^\d]/g, '');

        // 포맷팅된 숫자를 다시 설정
        $(this).text(formatNumberWithCommas(currentBalance) + '원');
    });
});

// 숫자 포맷 함수
function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

