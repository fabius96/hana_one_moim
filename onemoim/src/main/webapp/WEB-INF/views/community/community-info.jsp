<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 모임관리</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/community-info.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}" data-gathering-id="${gathering.gatheringId}">

<div class="except-footer">
    <jsp:include page="../includes/header_after_login.jsp"/>
    <jsp:include page="../includes/header-community.jsp"/>
    <div class="main-container">
        <div class="outer-content-wrapper">
            <div class="page-name-wrapper">
                <p class="page-name">모임관리</p>
                <div class="page-name-right">
                    <c:if test="${loggedInMemberId == gatheringLeaderId }">
                        <button class="invite-button">모임원 초대</button>
                        <button class="edit-button">모임 정보 수정</button>
                    </c:if>
                </div>
            </div>
            <table class="gathering-member-table">
                <thead>
                <tr>
                    <th>이름</th>
                    <th>상태</th>
                    <th>가입시간</th>
                    <th>상태변경시간</th>
                    <th></th>
                </tr>
                </thead>
                <tbody class="member-content">
                <c:forEach var="member" items="${gatheringMembers}">
                    <tr class="member-item">
                        <td>${member.memberName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${member.memberStatusCode == 70}"><p class="active-p">활동</p></c:when>
                                <c:when test="${member.memberStatusCode == 71}"><p class="pause-p">정지</p></c:when>
                                <c:when test="${member.memberStatusCode == 72}"><p class="standby-p">승인대기</p></c:when>
                                <c:otherwise>알 수 없음</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${member.createdAt}</td>
                        <td>${member.modifiedAt}</td>
                        <td>
                            <c:if test="${member.memberId == gatheringLeaderId}">
                                <img src="/img/crown.png" class="crown-img" alt="모임장">
                            </c:if>
                            <c:if test="${loggedInMemberId == gatheringLeaderId && member.memberId != gatheringLeaderId}">
                                <c:choose>
                                    <c:when test="${member.memberStatusCode == 70}">
                                        <button class="status-change-button pause" data-member-id="${member.memberId}"
                                                data-member-status-code="71">
                                            정지
                                        </button>
                                    </c:when>
                                    <c:when test="${member.memberStatusCode == 71}">
                                        <button class="status-change-button active" data-member-id="${member.memberId}"
                                                data-member-status-code="70">
                                            활동
                                        </button>
                                    </c:when>
                                    <c:when test="${member.memberStatusCode == 72}">
                                        <button class="status-change-button approve" data-member-id="${member.memberId}"
                                                data-member-status-code="70">
                                            승인
                                        </button>
                                    </c:when>
                                </c:choose>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp"/>
<script src="/js/community-info.js"></script>
</body>
</html>
