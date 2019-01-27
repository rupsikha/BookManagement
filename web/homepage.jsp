<%--
  Created by IntelliJ IDEA.
  User: Kaushik
  Date: 26-Jan-19
  Time: 1:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Home</title>
</head>
<body>
    Welcome ${username}
    <form method="post" target="_self" action="${pageContext.request.contextPath}/logout">
        <input type="submit" value="logout">
    </form>

    <center> My Books </center>
    <table border="1px">
        <c:forEach var="book" items="${mybooks}">
            <tr>
                <td>${book.bookid}</td>
                <td>${book.bookname}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <br>
    <br>
    <br>


    <center> Others Books </center>
    <form action="${pageContext.request.contextPath}/book" target="_self" method="post">
    <table border="1px">
        <c:forEach var="book" items="${othersbooks}">
            <tr>
                <td> <input type="checkbox" name="bookids" value="${book.bookid}"> </td>
                <td>${book.bookid}</td>
                <td>${book.userDetails.username}</td>
                <td>${book.bookname}</td>
            </tr>
        </c:forEach>
    </table><br>
        <input type="submit" value="lend me these books"/>
    </form>
</body>
</html>
