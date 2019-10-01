<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <span style="color: red">${errorMsg}</span>
    <form action="/login" method="post">
        账号：<input type="text" name="username"><br>
        密码：<input type="password" name="password"><br>
        <a href="/registerr.jsp">   注册  </a>
        <input type="submit" value="登录">
    </form>
</body>
</html>
