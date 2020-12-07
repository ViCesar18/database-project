<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 07/12/2020
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Editar Post</title>
</head>
<body>
<div class="container">
    <h1>Edite seu post</h1>

    <form
            action="${pageContext.servletContext.contextPath}/editar-post"
            method="post"
    >
        <input type="hidden" name="id" value="${post.getId()}">

        <div class="form-group">
            <label for="textoPost"></label>
            <input type="text" required class="form-control" id="textoPost" name="textoPost" value="${post.getTextoPost()}">
        </div>

        <button type="submit" class="btn btn-primary">Atualizar Post</button>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
    </form>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>



