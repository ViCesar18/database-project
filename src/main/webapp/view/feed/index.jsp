<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 13/09/2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical</title>
    </head>
    <body>
        <div class="container">
            <div style="margin: 10px">
                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Seu Perfil</a>

                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
            </div>
        </div>

        <div class="container">
            <h1>Pesquise um usuário, banda ou evento!</h1>
            <form
                    class="form"
                    action="${pageContext.servletContext.contextPath}/pesquisa"
                    method="post"
            >
                <div class="form-group">
                    <input type="text" required class="form-control" id="inputPesquisa" name="pesquisa">
                </div>

                <div class="form-group form-check">
                    <input type="checkbox" class="form-check-input" id="filtroUsuario" name="filtroUsuario">
                    <label class="form-check-label" for="filtroUsuario" style="margin-right: 25px">Usuário</label>

                    <input type="checkbox" class="form-check-input" id="filtroBanda" name="filtroBanda">
                    <label class="form-check-label" for="filtroBanda"style="margin-right: 25px">Banda</label>

                    <input type="checkbox" class="form-check-input" id="filtroEvento" name="filtroEvento">
                    <label class="form-check-label" for="filtroEvento">Evento</label>
                </div>
                <c:if test="${requestScope.erroPesquisa}">
                    <p style="color: red">Você deve selecionar ao menos um filtro!</p>
                </c:if>
                <button type="submit" class="btn btn-primary">Pesquisar</button>
            </form>
        </div>
        <div class="container">
            <form
                    class="form"
                    action="${pageContext.servletContext.contextPath}/publicar-post"
                    method="post"
            >
                <div class="form-group">
                    <label for="textoPost">O que te inspira no dia de hoje?</label>
                    <input type="text" required class="form-control" id="textoPost" name="textoPost">
                </div>

                <button type="submit" class="btn btn-primary">Publicar</button>
            </form>
        </div>
    </body>
</html>
