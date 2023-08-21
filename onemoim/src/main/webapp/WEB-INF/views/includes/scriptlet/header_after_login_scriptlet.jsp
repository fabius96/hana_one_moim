<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- scriptlet -->
<%
    com.hana.onemoim.member.dto.MemberDto loggedInMember = (com.hana.onemoim.member.dto.MemberDto) session.getAttribute("loggedInMember");
    String name = (loggedInMember != null) ? loggedInMember.getName() : "Unknown";
    request.setAttribute("name", name);
%>