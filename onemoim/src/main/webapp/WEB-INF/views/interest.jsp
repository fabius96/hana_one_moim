<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관심사 설정</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/interest.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
<div class="except-footer">
    <jsp:include page="includes/header_after_login.jsp"/>

    <div class="main-container">
        <div class="content-wrapper">
            <p class="page-name">관심사 설정</p>

            <form action="/interest" method="post" id="interest-form">
                <input type="hidden" name="memberId" value="${memberId}"/>
                <div class="container">
                    <p class="header-text">
                        모임 관심사 설정
                    </p>
                    <p class="sub-text">
                        내가 개설 혹은 가입하고 싶은 모임의 관심사를 설정해주세요.<br/>
                        최대 3개의 영역까지 선택 가능합니다.
                    </p>
                    <div class="row">
                        <label>
                            <input type="checkbox" name="interestNames" value="가족"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-people-roof" aria-hidden="true"></i>
                                <span>가족</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="커플"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-heart" aria-hidden="true"></i>
                                <span>커플</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="학교/동아리"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-school" aria-hidden="true"></i>
                                <span>학교/동아리</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="회사/팀"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-building" aria-hidden="true"></i>
                                <span>회사/팀</span>
                            </div>
                        </label>

                        <label>
                            <input type="checkbox" name="interestNames" value="취미/동호회"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-people-group" aria-hidden="true"></i>
                                <span>취미/동호회</span>
                            </div>
                        </label>

                        <label>
                            <input type="checkbox" name="interestNames" value="스터디"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-pen" aria-hidden="true"></i>
                                <span>스터디</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="게임"/>
                            <div class="icon-box">
                                <i class="fa fa-gamepad" aria-hidden="true"></i>
                                <span>게임</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="스포츠/레저"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-futbol" aria-hidden="true"></i>
                                <span>스포츠/레저</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="헬스/다이어트"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-dumbbell" aria-hidden="true"></i>
                                <span>헬스/다이어트</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="음악"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-music" aria-hidden="true"></i>
                                <span>음악</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="맛집/요리"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-utensils" aria-hidden="true"></i>
                                <span>맛집/요리</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="여행/캠핑"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-binoculars" aria-hidden="true"></i>
                                <span>여행/캠핑</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="종교/봉사"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-cross" aria-hidden="true"></i>
                                <span>종교/봉사</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="문화/예술"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-palette" aria-hidden="true"></i>
                                <span>문화/예술</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="영화/애니"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-film" aria-hidden="true"></i>
                                <span>영화/애니</span>
                            </div>
                        </label>
                        <label>
                            <input type="checkbox" name="interestNames" value="독서"/>
                            <div class="icon-box">
                                <i class="fa-solid fa-book" aria-hidden="true"></i>
                                <span>독서</span>
                            </div>
                        </label>
                    </div>
                </div>
                <button type="submit" class="setting-button">
                    <a class="button-text">설정</a>
                </button>
            </form>

        </div>
    </div>


</div>


</div>
<jsp:include page="includes/footer.jsp"/>
<script src="https://kit.fontawesome.com/f87e59f65d.js" crossorigin="anonymous"></script>
<script src="/js/interest.js"></script>
</body>
</html>
