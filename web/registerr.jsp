<%--
  Created by IntelliJ IDEA.
  User: 11714
  Date: 2019/9/24
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<script type="text/javascript">
    function reflish() {
        //通过id获取元件
        document.getElementById("randomImg").src = "/randomCode?"+new Date().getTime();
    }
</script>
<span style="color: red">${errorMsg}</span>
<form action="/register" method="post">
    请输入账号：<input type="text" name="username" ><br>
    请输入密码：<input type="password" name="password"><br>
        验证码：<input type="text" name="randomCode" size="5" maxlength="5">
    <img src="/randomCode" id="randomImg" title="看不清，换一张"
         onclick="reflish();"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
