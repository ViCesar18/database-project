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
                <img
                        src="${pageContext.request.contextPath}/assets/img/logo.png"
                        class="rounded-circle"
                        alt="Avatar"
                >
                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Seu Perfil</a>

                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
            </div>
        </div>

        <div class="container">
            <p><strong>Pesquise um usuário, banda ou evento!</strong></p>
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
        <div class="container" style="margin-top: 40px">
            <form
                    class="form"
                    action="${pageContext.servletContext.contextPath}/publicar-post"
                    method="post"
                    enctype="multipart/form-data"
            >
                <div class="form-group">
                    <label for="textoPost"><strong>O que te inspira no dia de hoje?</strong></label>
                    <input type="text" required class="form-control" id="textoPost" name="textoPost">
                </div>
                <div class="form-group">
                    <label for="inputImagem">Foto</label>
                    <input type="file" class="form-control-file" id="inputImagem" name="imagem">
                </div>

                <button type="submit" class="btn btn-primary">Publicar</button>
                <hr color="grey">
            </form>
        </div>
        <c:if test="${requestScope.posts != null}">
            <c:forEach var="post" items="${requestScope.posts}">
                <div class="container">
                    <div style="display: flex; flex-direction: row">
                        <img
                                src="${pageContext.request.contextPath}/assets/img/usuario/${post.usuario.imagem}"
                                class="rounded-circle"
                                alt="Avatar"
                                height="50"
                                width="50"
                        >
                        <div>
                            <p><strong>${post.usuario.pNome} ${post.usuario.sNome}</strong></p>
                            <p>${post.dtPublicacao}</p>
                            <c:if test="${usuario.getId() == post.usuarioId}">
                                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/apagar-post?id=${post.id}">Apagar post</a>
                                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/editar-post?id=${post.id}">Editar post</a>
                            </c:if>
                            <hr>
                        </div>
                    </div>
                    <div widht="300">
                        <p>${post.textoPost}</p>
                        <br>
                            <c:if test="${post.imagem != null}">
                                <img
                                        src="${pageContext.request.contextPath}/assets/img/post/${post.imagem}"
                                        alt="Avatar"
                                >
                            </c:if>
                        <br><br>
                    </div>
                    <div>
                        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Curtir</a>
                        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Comentar</a>
                        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Compartilhar</a>
                        <hr color="grey">
                    </div>
                </div>
            </c:forEach>
        </c:if>
    <c:if test="${requestScope.posts.size() == 0}">
        <h1 class="container" align="center">Seu feed ainda não têm posts. Faça um agora mesmo!</h1>
    </c:if>
        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
