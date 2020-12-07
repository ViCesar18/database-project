<<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 06/12/2020
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Escolher instrumento</title>
</head>
<body>
<div class="container">
    <h1>Escolha seu instrumento</h1>

    <form
            action="${pageContext.servletContext.contextPath}/participar-banda"
            method="post"
    >
        <input type="hidden" name="id" value="${banda.getId()}">

        <div class="form-group">
            <label for="inputInstrumento">Escolha seu instrumento</label>
            <input type="text" required class="form-control" id="inputInstrumento" name="instrumento">
        </div>

        <button type="submit" class="btn btn-primary">Escolher</button>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil?id=${banda.getId()}">Voltar</a>
    </form>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>


