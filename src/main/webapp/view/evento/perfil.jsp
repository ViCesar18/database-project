<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 23/11/2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Evento" %>
<%@ page import="model.Usuario" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Perfil do Evento</title>
</head>
<body>
<%
    Evento evento = (Evento)request.getAttribute("evento");
    Usuario usuarioLogado = (Usuario)session.getAttribute("usuario");

    boolean criador;
    if (usuarioLogado.getId() == evento.getUsername_id()){
        request.setAttribute("criador", true);
    }
    else{
        request.setAttribute("criador", false);
    }
%>
<div class="text-center">
        <h1>${evento.getNome()}</h1>
    </div>

    <div class="text-center">
        <p><strong>Descrição:</strong> ${evento.getDescricao()}</p>
        <p><strong>Categoria:</strong> ${evento.getCategoria()}</p>
        <p><strong>Início:</strong> ${evento.getData_inicio().toString()}</p>
        <p><strong>Término:</strong> ${evento.getData_termino().toString()}</p>
        <p><strong>Local:</strong> ${evento.getNome_local()} | Rua ${evento.getRua()} ${evento.getNumero()}, ${evento.getBairro()} - ${evento.getCep()}</p>
        <c:if test="${requestScope.criador}">
            <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/evento/perfil/delete?id=${evento.getId()}">Deletar evento</a>
            <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/evento/perfil/update?id=${evento.getId()}">Editar evento</a>
        </c:if>
    <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>
