<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>after_login_main</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/after-login-main.css">
</head>
<body>

<div class="except-footer">
    <jsp:include page="includes/header_after_login.jsp"/>
    <div class="main-container">

        <div class="sub-container">
            <div class="slider-wrapper">
                <div class="container" id="first-container">
                    <img src="<%= request.getContextPath() %>/img/main.png" alt="슬라이드이미지1" class="container-image"/>
                    <p class="slogan">
                        <span>새로운 모임을 만들고 </span><br/>
                        <span>친구들과 추억을 나눠보세요</span>
                    </p>
                    <p class="hash-tag">#가족 #학교, 동아리 #취미, 동호회 #스터디 #회사, 팀</p>
                    <div class="create-group-button">
                        <button type="submit" class="button-text"
                                onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-create'">
                            모임만들기
                        </button>
                    </div>
                </div>
                <div class="container" id="second-container">
                    <img src="<%= request.getContextPath() %>/img/travel.png" alt="슬라이드이미지1" class="container-image-2"/>
                    <p class="slogan">
                        <span>내게 맞는 모임을 </span><br/>
                        <span>찾아보세요</span>
                    </p>
                    <p class="hash-tag">#스포츠, 레저 #여행, 캠핑 #친목, 모임 #나이, 또래모임</p>
                    <div class="create-group-button">
                        <button type="submit" class="button-text"
                                onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-create'">
                            모임검색
                        </button>
                    </div>
                </div>
            </div>

            <div class="right-container">
                <div class="boxes-container">
                    <div class="big-link-button" id="link-button-group">
                        <button type="button" class="link-button-text" id="link-button-text-group"
                                onclick="location.href='${pageContext.request.contextPath}/gathering/gathering-info'">모임
                        </button>
                    </div>

                    <div class="big-link-button" id="link-button-openbanking">
                        <button type="submit" class="link-button-text" id="link-button-text-openbanking">오픈뱅킹</button>
                    </div>
                </div>
                <div class="gathering-container">
                    <div class="third-gathering-icon">
                        <img src="<%= request.getContextPath() %>/img/category.png" alt="모임아이콘1"
                             class="gathering-image" onclick="showGatheringCategory();">
                        <p class="icon-name">모임분류</p>
                    </div>
                    <div class="second-gathering-icon">
                        <img src="<%= request.getContextPath() %>/img/recommend.png" alt="모임아이콘2"
                             class="gathering-image">
                        <p class="icon-name">모임추천</p>
                    </div>
                    <div class="first-gathering-icon">
                        <img src="<%= request.getContextPath() %>/img/add.png" alt="모임아이콘3"
                             class="gathering-image" onclick="createGathering();">
                        <p class="icon-name" onclick="createGathering();">모임생성</p>
                    </div>
                </div>
                <div class="search-container">
                    <form action="<%= request.getContextPath() %>/gathering/gathering-search" method="get"
                          class="search-form">
                        <input type="text" placeholder="모임 검색" class="search-bar" name="keyword">
                        <button type="submit" class="search-btn"></button>
                    </form>
                </div>
            </div>

        </div>

    </div>
    <div class="recommend-area">
        <p class="area-title">주제별 모임을 찾아보세요</p>
        <div class="recommend-container">
            <button id="prevBtn" class="scroll-btn">
                <img src="<%= request.getContextPath() %>/img/left-arrow.png" class="scroll-btn-img"
                     alt="좌측화살표">
            </button>
            <div class="recommend-slider">
                <div class="recommend-item" id="recommend-item-1" onclick="showGatheringCategory('취미/동호회');">
                    <img src="<%= request.getContextPath() %>/img/c1-hobby.jpg" class="recommend-item-img"
                         alt="모임이미지1">
                    <p class="item-title">취미/동호회</p>
                </div>
                <div class="recommend-item" id="recommend-item-2" onclick="showGatheringCategory('스터디');">
                    <img src="<%= request.getContextPath() %>/img/c2-study.jpg" class="recommend-item-img"
                         alt="모임이미지2">
                    <p class="item-title">스터디</p>
                </div>
                <div class="recommend-item" id="recommend-item-3" onclick="showGatheringCategory('게임');">
                    <img src="<%= request.getContextPath() %>/img/c3-game.jpg" class="recommend-item-img"
                         alt="모임이미지3">
                    <p class="item-title">게임</p>
                </div>
                <div class="recommend-item" id="recommend-item-4" onclick="showGatheringCategory('스포츠/레저');">
                    <img src="<%= request.getContextPath() %>/img/c4-sport.jpg" class="recommend-item-img"
                         alt="모임이미지4">
                    <p class="item-title">스포츠/레저</p>
                </div>
                <div class="recommend-item" id="recommend-item-5" onclick="showGatheringCategory('헬스/다이어트');">
                    <img src="<%= request.getContextPath() %>/img/c5-health.jpg" class="recommend-item-img"
                         alt="모임이미지5">
                    <p class="item-title">헬스/다이어트</p>
                </div>
                <div class="recommend-item" id="recommend-item-6" onclick="showGatheringCategory('음악');">
                    <img src="<%= request.getContextPath() %>/img/c6-music.jpg" class="recommend-item-img"
                         alt="모임이미지6">
                    <p class="item-title">음악</p>
                </div>
                <div class="recommend-item" id="recommend-item-7" onclick="showGatheringCategory('맛집/요리');">
                    <img src="<%= request.getContextPath() %>/img/c7-food.jpg" class="recommend-item-img"
                         alt="모임이미지7">
                    <p class="item-title">맛집/요리</p>
                </div>
                <div class="recommend-item" id="recommend-item-8" onclick="showGatheringCategory('여행/캠핑');">
                    <img src="<%= request.getContextPath() %>/img/c8-travel.jpg" class="recommend-item-img"
                         alt="모임이미지8">
                    <p class="item-title">여행/캠핑</p>
                </div>
                <div class="recommend-item" id="recommend-item-9" onclick="showGatheringCategory('종교/봉사');">
                    <img src="<%= request.getContextPath() %>/img/c9-religion.jpg" class="recommend-item-img"
                         alt="모임이미지9">
                    <p class="item-title">종교/봉사</p>
                </div>
                <div class="recommend-item" id="recommend-item-10" onclick="showGatheringCategory('문화/예술');">
                    <img src="<%= request.getContextPath() %>/img/c10-art.jpg" class="recommend-item-img"
                         alt="모임이미지10">
                    <p class="item-title">문화/예술</p>
                </div>
                <div class="recommend-item" id="recommend-item-11" onclick="showGatheringCategory('영화/애니');">
                    <img src="<%= request.getContextPath() %>/img/c11-movie.jpg" class="recommend-item-img"
                         alt="모임이미지11">
                    <p class="item-title">영화/애니</p>
                </div>
                <div class="recommend-item" id="recommend-item-12" onclick="showGatheringCategory('독서');">
                    <img src="<%= request.getContextPath() %>/img/c12-book.jpg" class="recommend-item-img"
                         alt="모임이미지12">
                    <p class="item-title">독서</p>
                </div>
            </div>
            <button id="nextBtn" class="scroll-btn">
                <img src="<%= request.getContextPath() %>/img/right-arrow.png" class="scroll-btn-img"
                     alt="우측화살표">
            </button>
        </div>
    </div>

</div>

<jsp:include page="includes/footer.jsp"/>
<script src="/js/after-login-main.js"></script>
</body>
</html>