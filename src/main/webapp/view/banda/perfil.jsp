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
        imgName = "img/banda/default_avatar.png";
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
        <p><strong>Participantes</strong> ${requestScope.participantes}</p>
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
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

