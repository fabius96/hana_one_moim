<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>일정모달</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/calendar-modal.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body data-context-path="${pageContext.request.contextPath}">
<div class="modal" id="eventModal">
    <div class="modal-content">

        <span class="close" data-dismiss="modal">&times;</span>

        <p class="modal-title">일정등록</p>


        <div class="modal-body">
            <table>
                <tr>
                    <td><label for="edit-allDay">하루종일</label></td>
                    <td><input class="allDayNewEvent" id="edit-allDay" type="checkbox"></td>
                </tr>
                <tr>
                    <td><label for="edit-title">일정명</label></td>
                    <td><input class="inputModal" type="text" name="edit-title" id="edit-title" required="required">
                    </td>
                </tr>
                <tr>
                    <td><label for="edit-start">일정시작시간</label></td>
                    <td><input class="inputModal" type="datetime-local" name="edit-start" id="edit-start"></td>
                </tr>
                <tr>
                    <td><label for="edit-end">일정종료시간</label></td>
                    <td><input class="inputModal" type="datetime-local" name="edit-end" id="edit-end"></td>
                </tr>
                <tr>
                    <td><label for="edit-color">색상</label></td>
                    <td>
                        <select class="inputModal" name="color" id="edit-color">
                            <option value="#D25565" style="color:#D25565;">빨간색</option>
                            <option value="#9775fa" style="color:#9775fa;">보라색</option>
                            <option value="#ffa94d" style="color:#ffa94d;">주황색</option>
                            <option value="#74c0fc" style="color:#74c0fc;">파란색</option>
                            <option value="#f06595" style="color:#f06595;">핑크색</option>
                            <option value="#63e6be" style="color:#63e6be;">연두색</option>
                            <option value="#a9e34b" style="color:#a9e34b;">초록색</option>
                            <option value="#4d638c" style="color:#4d638c;">남색</option>
                            <option value="#495057" style="color:#495057;">검정색</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="edit-desc">일정설명</label></td>
                    <td><textarea rows="4" cols="50" class="inputModal" name="edit-desc" id="edit-desc"></textarea></td>
                </tr>
            </table>
        </div>
        <div class="modal-footer modalBtnContainer-addEvent">
            <button class="delete-btn" id="deleteEvent">삭제</button>
            <button class="save-btn" id="save-event">저장</button>
            <button class="close-btn" data-dismiss="modal">취소</button>
        </div>
    </div>
</div>
<script src="/js/calendar-modal.js"></script>
</body>
</html>
