<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 23/11/2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Evento" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Perfil do Evento</title>
</head>
<body>
<%
    Evento evento = (Evento)request.getAttribute("evento");
%>
<div class="text-center">
        <h1>${evento.getNome()}</h1>
    </div>

    <div class="text-center">
        <p><strong>Descrição:</strong> ${evento.getDescricao()}</p>
        <p><strong>Categoria:</strong> ${evento.getCategoria()}</p>
        <p><strong>Início:</strong> ${evento.getData_inicio().toString()}</p>
        <p><strong>Término:</strong> ${evento.getData_termino().toString()}</p>
        <p><strong>Local:</strong> Rua ${evento.getRua()} ${evento.getNumero()}, ${evento.getBairro()} - ${evento.getCep()}</p>
    </div>
    <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
</div>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>
