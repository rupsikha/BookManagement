<%--
  Created by IntelliJ IDEA.
  User: Kaushik
  Date: 27-Jan-19
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title> Login Page</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
      function GetCookie(sName){
        var aCookie = document.cookie.split("; ");
        for (var i=0; i < aCookie.length; i++){
          var aCrumb = aCookie[i].split("=");
          if (sName == aCrumb[0])
            return unescape(aCrumb[1]);
        }
        return null;
      }
      var username = GetCookie("username");
      // if(username != null) {
      //   $(location).attr('href','/login');
      // }
    </script>
  </head>
  <body>

    <form action="${pageContext.request.contextPath}/login" method="post" target="_self">
      Enter Username : <input type="text" name="username"/> <br>
      Enter Password : <input type="password" name="password"/> <br>
      <input type="submit" value="submit"/>
    </form>

  </body>
</html>
