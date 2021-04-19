<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 19/04/2021
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Estatísticas</title>
</head>
<body>
<div class="container">
    <div style="display: flex; flex-direction: row">
        <img
                src="${pageContext.request.contextPath}/assets/img/logo.png"
                class="rounded-circle"
                alt="Avatar"
        >
        <div>
            <br>
            <h1>Estatísticas</h1>
        </div>
        </div>
    </div>



<%@include file="../include/scripts.jsp"%>
</body>
</html>
