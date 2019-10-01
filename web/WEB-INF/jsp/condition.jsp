<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询</title>
</head>
<body>
    <form action="/student?cmd=condition" method="post">
        姓名：<input type="text" name="studentName"><br>
        年龄：<input type="number" name="minAge">-<input type="number" name="maxAge"><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
