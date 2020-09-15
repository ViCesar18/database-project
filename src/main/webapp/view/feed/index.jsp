<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 13/09/2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical</title>
    </head>
    <body>
        <div style="margin: 10px">
            <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/perfil">Seu Perfil</a>

            <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

            <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/all">Ver Bandas</a>

            <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

            <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/all">Ver Eventos</a>

            <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
        </div>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
