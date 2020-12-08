<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 13/09/2020
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Usuario" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical: Perfil</title>
    </head>
    <body>
        <%
            Usuario user = (Usuario)request.getAttribute("usuario");
            String imgName = user.getImagem();

            if(imgName == null || imgName.isBlank()) {
                imgName = "default_avatar.png";
            }

            request.setAttribute("imgName", imgName);

            Date dt = user.getDtNascimento();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(dt.getTime()));

            request.setAttribute("date", date);
        %>
        <div class="container">
            <div style="margin: 10px">
                <img
                        src="${pageContext.request.contextPath}/assets/img/logo.png"
                        class="rounded-circle"
                        alt="Avatar"
                >
                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/feed">Página Inicial</a>

                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
            </div>
        </div>
        <div class="container">
            <div class="text-center">
                <img
                    src="${pageContext.request.contextPath}/assets/img/usuario/${imgName}"
                    class="rounded-circle"
                    alt="Avatar"
                    height="350"
                    width="350"
                    style="object-fit: cover"
                >

                <h1>${usuario.getpNome()} ${usuario.getsNome()}</h1>
            </div>

            <div>
                <p><strong>Seguidores:</strong> ${seguidores} <ta <strong>Seguindo:</strong> ${seguindo}</p>
                <p><strong>Email:</strong> ${usuario.getEmail()}</p>
                <p><strong>Data de Nascimento:</strong> ${date}</p>
                <p><strong>Email:</strong> ${usuario.getEmail()}</p>
                <p><strong>Local:</strong> ${usuario.getCidade()}/${usuario.getEstado()}/${usuario.getPais()}</p>
                <p><strong>Banda Favorita:</strong> ${usuario.getBandaFavorita()}</p>
                <p><strong>Música Favorita:</strong> ${usuario.getMusicaFavorita()}</p>
                <p><strong>Gênero Favorito:</strong> ${usuario.getGeneroFavorito()}</p>
                <p><strong>Instrumento Favorito:</strong> ${usuario.getInstrumentoFavorito()}</p>
                <p>
                    <strong>Instrumentos que Toca:</strong>
                    <c:forEach var="instrumento" items="${requestScope.instrumentos}">
                        <p>${instrumento}</p>
                    </c:forEach>
                </p>

                <br>
                <c:if test="${sessionScope.usuario.id == requestScope.usuario.id}">
                    <form
                            class="form"
                            action="${pageContext.servletContext.contextPath}/usuario/instrumentos"
                            method="post"
                    >
                        <div class="form-group">
                            <label for="inputInstrumentoQueToca">Adicionar Novo Instrumento:</label>
                            <input type="text" required class="form-control" id="inputInstrumentoQueToca" name="instrumentoQueToca">
                        </div>

                        <button type="submit" class="btn btn-primary">Adicionar Instrumento</button>
                    </form>
                </c:if>
            </div>

            <div>
                <c:if test="${sessionScope.usuario.id == requestScope.usuario.id}">
                    <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update">Editar Perfil</a>

                    <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update-musical">Editar Perfil Musical</a>

                    <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update-foto">Alterar Foto de Perfil</a>

                    <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update-senha">Alterar Senha</a>
                </c:if>
                <c:if test="${sessionScope.usuario.id != requestScope.usuario.id}">
                    <c:if test="${!requestScope.segue}">
                        <button
                                type="button"
                                class="btn btn-success"
                                onclick="seguirUsuario(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${requestScope.usuario.id})"
                        >
                            Seguir
                        </button>
                    </c:if>
                    <c:if test="${requestScope.segue}">
                        <button
                                type="button"
                                class="btn btn-warning"
                                onclick="pararSeguirUsuario(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${requestScope.usuario.id})"
                        >
                            Deixar de Seguir
                        </button>
                    </c:if>
                </c:if>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
            </div>

            <div class="row justify-content-end">
                <c:if test="${sessionScope.usuario.id == requestScope.usuario.id}">
                    <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil/delete">Deletar Sua Conta</a>
                </c:if>
            </div>
        </div>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
