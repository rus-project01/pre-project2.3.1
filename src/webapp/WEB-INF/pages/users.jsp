<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<body>
<h3 align="center">Users List</h3>
<c:if test="${!empty listPersons}">
    <table align="center" border="1" cellpadding="5">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Age</th>
            <th width="120">Street</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listPersons}" var="person">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.age}</td>
                <td>${person.street}</td>
                <td><a href="<c:url value='/cars/edit?id=${person.id}'/>" >Edit</a></td>
                <td><a href="<c:url value='/cars/delete?id=${person.id}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<h2 align="center" border="1" cellpadding="5"><a href="<c:url value='/cars/newuser' />" >Create User</a></h2>
</body>
</html>
