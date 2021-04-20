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
                    <p style="color: #ff0000">Você deve selecionar ao menos um filtro!</p>
                </c:if>
                <button type="submit" class="btn btn-primary">Pesquisar</button>
            </form>
        </div>
        <hr style="background-color: rgba(0, 0, 0, 0.1); margin-bottom: 20px">
        <div class="container">
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
                    <label for="inputImagem">Imagem</label>
                    <input type="file" class="form-control-file" id="inputImagem" name="imagem">
                </div>

                <button type="submit" class="btn btn-primary">Publicar</button>
                <hr style="background-color: rgba(0, 0, 0, 0.1); margin-bottom: 50px">
            </form>
        </div>
        <c:if test="${requestScope.posts != null}">
            <c:forEach var="post" items="${requestScope.posts}">
                <div class="container">
                    <div style="display: flex; flex-direction: row; justify-content: space-between">
                        <div style="display: flex; flex-direction: row">
                            <div style="display: flex; flex-direction: row; align-items: center">
                                <img
                                        src="${pageContext.request.contextPath}/assets/img/usuario/${post.usuario.imagem != null ? post.usuario.imagem : "default_avatar.png"}"
                                        class="rounded-circle"
                                        alt="Avatar"
                                        height="50"
                                        width="50"
                                        style="object-fit: cover"
                                >
                                <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                    <p style="height: 5px"><strong>${post.usuario.pNome} ${post.usuario.sNome}</strong></p>
                                    <p style="height: 5px;"><i>${post.dtPublicacao}</i></p>
                                </div>
                            </div>
                            <c:if test="${not empty post.banda}">
                                <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20px">
                                    <img
                                            src="${pageContext.request.contextPath}/assets/img/banda/${post.banda.imagem != null ? post.banda.imagem : "default_avatar.png"}"
                                            class="rounded-circle"
                                            alt="Avatar"
                                            height="50"
                                            width="50"
                                            style="object-fit: cover"
                                    >
                                    <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                        <p style="height: 5px"><strong>${post.banda.nome} (${post.banda.sigla})</strong></p>
                                        <p style="height: 5px;"><i>${post.dtPublicacao}</i></p>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div>
                            <c:if test="${usuario.getId() == post.usuarioId}">
                                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/editar-post?id=${post.id}">Editar post</a>
                                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/apagar-post?id=${post.id}">Apagar post</a>
                            </c:if>
                        </div>
                    </div>
                    <div style="margin-top: 20px">
                        <p>${post.textoPost}</p>
                            <c:if test="${post.imagem != null}">
                                <img
                                        src="${pageContext.request.contextPath}/assets/img/post/${post.imagem}"
                                        style="width: 100%; height: 75%; object-fit: contain"
                                >
                            </c:if>
                        <p style="margin-top: 20px">
                            <strong>Curtidas:</strong> ${post.nCurtidas} <strong>Compartilhamento:</strong> ${post.nCompartilhamentos} <strong>Comentários:</strong> ${post.nComentarios}
                        </p>
                    </div>
                    <div>
                        <c:choose>
                            <c:when test="${!post.curtiu}">
                                <button
                                        type="button" class="btn btn-primary"
                                        onclick="curtirPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
                                        style="margin-right: 10px"
                                >Curtir</button>
                            </c:when>
                            <c:otherwise>
                                <button
                                        type="button" class="btn btn-warning"
                                        onclick="descurtirPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
                                >Descurtir</button>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${!post.compartilhou}">
                                <button
                                        type="button"
                                        class="btn btn-primary"
                                        onclick="compartilharPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
                                        style="margin-right: 10px"
                                >Compartilhar</button>
                            </c:when>
                            <c:otherwise>
                                <button
                                        type="button"
                                        class="btn btn-warning"
                                        onclick="descompartilharPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
                                >Não Compartilhar</button>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${post.fiveFirstComentarios != null}">
                            <c:forEach var="comentario" items="${post.fiveFirstComentarios}">
                                <div>
                                    <div style="display: flex; flex-direction: row; margin-top: 20px; align-items: center">
                                        <img
                                                src="${pageContext.request.contextPath}/assets/img/usuario/${comentario.usuario.imagem != null ? comentario.usuario.imagem : "default_avatar.png"}"
                                                class="rounded-circle"
                                                alt="Avatar"
                                                height="50"
                                                width="50"
                                                style="object-fit: cover"
                                        >
                                        <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                            <p style="height: 5px"><strong>${comentario.usuario.pNome} ${comentario.usuario.sNome}</strong></p>
                                            <p style="height: 5px"><i>${comentario.dtPublicacao}</i></p>
                                        </div>
                                        <div style="margin-left: 20px; justify-self: start">
                                            <p>${comentario.textoComentario}</p>
                                        </div>
                                    </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${post.nComentarios > 5}">
                            <div align="center">
                                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/feed/post?id=${post.id}">Ver todos os comentarios</a>
                            </div>
                        </c:if>
                        <div style="margin-top: 20px">
                            <form
                                    class="form"
                                    action="${pageContext.servletContext.contextPath}/publicar-comentario?idPost=${post.id}"
                                    method="post"
                            >
                                <div class="form-group">
                                    <label for="textoComentario"><strong></strong></label>
                                    <input type="text" required class="form-control" id="textoComentario" name="textoComentario">
                                </div>

                                <div align="right">
                                    <button type="submit" class="btn btn-primary">Comentar</button>
                                </div>
                            </form>
                        </div>
                        <hr style="background-color: rgba(0, 0, 0, 0.1); margin-bottom: 50px">
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${requestScope.posts.size() == 0}">
            <h1 class="container" align="center">Seu feed ainda não têm posts. Faça um agora mesmo!</h1>
        </c:if>

        <%@include file="../include/scripts.jsp"%>
    </div>
    </body>
</html>
