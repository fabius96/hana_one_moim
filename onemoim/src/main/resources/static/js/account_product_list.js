// 상품명 넘기기
$(document).ready(function() {
    $('.open-button').click(function() {
        var productName = $(this).data('product-name');
        window.location.href = '/account_opening?productName=' + encodeURIComponent(productName);
    });
});