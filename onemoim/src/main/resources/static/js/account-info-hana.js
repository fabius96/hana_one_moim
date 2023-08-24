// 아코디언
function toggleCustomAccordion(elementId) {
    let allContentDivs = document.querySelectorAll('.accordion-collapse');
    allContentDivs.forEach(div => {
        if (div.id === elementId) {
            if (div.style.maxHeight) {
                div.style.maxHeight = null;
            } else {
                div.style.maxHeight = div.scrollHeight + "px";
            }
        } else {
            div.style.maxHeight = null;
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

