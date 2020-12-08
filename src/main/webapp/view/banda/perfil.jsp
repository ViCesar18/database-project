<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 23/11/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>
<%@ page import="model.Usuario" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Perfil da Banda</title>
</head>
<body>
<%
    Banda banda = (Banda)request.getAttribute("banda");
    Usuario usuarioLogado = (Usuario)session.getAttribute("usuario");
    String imgName = banda.getImagem();

    if(imgName == null || imgName.isBlank()) {
        imgName = "default_avatar.png";
    }

    boolean criador;
    if (usuarioLogado.getId() == banda.getUsername_id()){
        request.setAttribute("criador", true);
    }
    else{
        request.setAttribute("criador", false);
    }

    request.setAttribute("imgName", imgName);
%>
<div class="container">
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
    <div class="text-center">
        <img
                src="${pageContext.request.contextPath}/assets/img/banda/${imgName}"
                class="rounded-circle"
                alt="Avatar"
                height="350"
                width="350"
        >

        <h1>${banda.getNome()} (${banda.getSigla()})</h1>
    </div>

    <div>
        <p><strong>Integrantes:</strong> ${requestScope.participantes}</p>
        <p><strong>Seguidores:</strong> ${requestScope.seguidores}</p>
        <p><strong>Gênero:</strong> ${banda.getGenero()}</p>
    </div>
    <div>
        <c:if test="${requestScope.criador}">
            <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/banda/perfil/update?id=${banda.getId()}">Editar banda</a>
            <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/banda/perfil/update-foto?id=${banda.getId()}">Alterar imagem</a>
        </c:if>
        <c:if test="${!requestScope.criador}">
            <c:if test="${!requestScope.segue}">
                <button
                        type="button"
                        class="btn btn-success"
                        onclick="seguirBanda(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${requestScope.banda.id})"
                >
                    Seguir
                </button>
            </c:if>
            <c:if test="${requestScope.segue}">
                <button
                        type="button"
                        class="btn btn-warning"
                        onclick="pararSeguirBanda(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${requestScope.banda.id})"
                >
                    Deixar de Seguir
                </button>
            </c:if>
            <c:if test="${!requestScope.participa}">
                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/participar-banda?id=${banda.getId()}">Participar da banda</a>
            </c:if>
            <c:if test="${requestScope.participa}">
                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/parar-participar-banda?id=${banda.getId()}">Deixar de participar da banda</a>
            </c:if>
        </c:if>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
    </div>

    <div class="row justify-content-end">
        <c:if test="${requestScope.criador}">
            <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil/delete?id=${banda.getId()}">Deletar banda</a>
        </c:if>
    </div>

    <c:if test="${requestScope.participa}">
        <div class="container" style="margin-top: 40px">
            <form
                    class="form"
                    action="${pageContext.servletContext.contextPath}/banda/publicar-post?id=${requestScope.banda.id}"
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
    </c:if>
    <c:if test="${requestScope.posts != null}">
    <c:forEach var="post" items="${requestScope.posts}">
        <div class="container">
            <div style="display: flex; flex-direction: row; justify-content: space-between">
                <div style="display: flex; flex-direction: row">
                    <img
                            src="${pageContext.request.contextPath}/assets/img/usuario/${post.usuario.imagem != null ? post.usuario.imagem : "default_avatar.png"}"
                            class="rounded-circle"
                            alt="Avatar"
                            height="50"
                            width="50"
                    >
                    <div>
                        <p><strong>${post.usuario.pNome} ${post.usuario.sNome}</strong></p>
                        <p>${post.dtPublicacao}</p>
                        <hr>
                    </div>
                </div>
                <div>
                    <c:if test="${usuario.getId() == post.usuarioId}">
                        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/editar-post?id=${post.id}">Editar post</a>
                        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/apagar-post?id=${post.id}">Apagar post</a>
                    </c:if>
                </div>
            </div>
            <div widht="300">
                <p>${post.textoPost}</p>
                <br>
                <c:if test="${post.imagem != null}">
                    <img
                            src="${pageContext.request.contextPath}/assets/img/post/${post.imagem}"
                            style="width: 100%; height: 75%; object-fit: contain"
                    >
                </c:if>
                <br><br>
                <strong>Curtidas:</strong> ${post.nCurtidas} <strong>Compartilhamento:</strong> ${post.nCompartilhamentos} <strong>Comentários:</strong> ${post.nComentarios}
                <br><br>
            </div>
            <div>
                <c:choose>
                    <c:when test="${!post.curtiu}">
                        <button
                                type="button" class="btn btn-primary"
                                onclick="curtirPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
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
                        >Compartilhar</button>
                    </c:when>
                    <c:otherwise>
                        <button
                                type="button"
                                class="btn btn-warning"
                                onclick="descompartilharPost(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${post.id})"
                        >Não Compartilhar</button>
                    </c:otherwise>
                </c:choose> <br><br><br>
                <c:if test="${post.comentarios != null}">
                <c:forEach var="comentario" items="${post.comentarios}">
                <div>
                    <div style="display: flex; flex-direction: row">
                        <img
                                src="${pageContext.request.contextPath}/assets/img/usuario/${comentario.usuario.imagem}"
                                class="rounded-circle"
                                alt="Avatar"
                                height="50"
                                width="50"
                        >
                        <div>
                            <p><strong>${comentario.usuario.pNome} ${comentario.usuario.sNome}</strong></p>
                            <p>${comentario.dtPublicacao}</p><br>
                        </div>
                        <div widht="300">
                            <p>${comentario.textoComentario}</p>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
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
                    <hr color="grey">
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${requestScope.posts.size() == 0}">
        <h1 class="container" align="center">A banda ainda não têm posts.</h1>
    </c:if>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

