<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 24/11/2020
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Banda" %>

<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Editar Foto</title>
</head>
<body>
<%
    Banda banda = (Banda)request.getAttribute("banda");
    String imgName = banda.getImagem();

    if(imgName == null || imgName.isBlank()) {
        imgName = "default_avatar.png";
    }

    request.setAttribute("imgName", imgName);
%>
<div class="container">
    <div style="margin: 10px">
        <img
                src="${pageContext.request.contextPath}/assets/img/logo.png"
                class="rounded-circle"
                alt="Avatar"
        >
        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/feed">Página Inicial</a>

        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Seu Perfil</a>

        <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

        <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
    </div>
</div>
<div class="container">
    <div class="text-center">
        <h1>Edite sua foto.</h1>

        <img
                src="${pageContext.request.contextPath}/assets/img/banda/${imgName}"
                class="rounded-circle"
                alt="Avatar"
                height="350"
                width="350"
        >
    </div>

    <form
            action="${pageContext.request.contextPath}/banda/perfil/update-foto"
            method="post"
            enctype="multipart/form-data"
    >
        <input type="hidden" name="id" value="${banda.getId()}">
        <input type="hidden" name="sigla" value="${banda.getSigla()}">
        <input type="hidden" name="nome" value="${banda.getNome()}">

        <div class="form-group">
            <label for="inputImagem">Foto de Perfil</label>
            <input type="file" class="form-control-file" id="inputImagem" name="imagem">
        </div>

        <button type="submit" class="btn btn-primary">Atualizar Foto</button>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil">Voltar</a>
    </form>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

