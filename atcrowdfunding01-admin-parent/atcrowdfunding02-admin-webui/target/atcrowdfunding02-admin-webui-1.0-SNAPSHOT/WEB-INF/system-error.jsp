<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
系统异常
<%--从请求域中获得exception对象，打印错误对象--%>
${requestScope.exception.message}
</body>
</html>
