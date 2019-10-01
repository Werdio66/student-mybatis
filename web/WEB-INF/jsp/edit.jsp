<%--
  Created by IntelliJ IDEA.
  User: 11714
  Date: 2019/9/23
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生信息</title>
</head>
<body>
    <form action="/student?cmd=save&id=${stu.id}" method="post">
        姓名：          <input type="text" name="name" value="${stu.name}"><br>
        年龄：          <input type="text" name="age" value="${stu.age}"><br>
        数学成绩：      <input type="text" name="math" value="${stu.math}"><br>
        计算机成绩：    <input type="text" name="computer" value="${stu.computer}"><br>
        英语成绩：      <input type="text" name="english" value="${stu.english}"><br>
                        <input type="submit" value="保存"><br>
    </form>
</body>
</html>
