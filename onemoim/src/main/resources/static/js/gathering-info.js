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