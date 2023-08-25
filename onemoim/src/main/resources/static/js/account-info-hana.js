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

