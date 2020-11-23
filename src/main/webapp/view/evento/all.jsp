<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 15/09/2020
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Listar Eventos</title>
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="col h4">ID</th>
            <th class="col h4">Nome</th>
            <th class="col h4">Descriçao</th>
            <th class="col h4">Categoria</th>
            <th class="col h4">Data de Inicio</th>
            <th class="col h4">Data de Termino</th>
            <th class="col h4">Local</th>
            <th class="col h4">Id Criador</th>
            <th class="col h4">Numero de participantes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="evento" items="${requestScope.eventos}">
            <tr>
                <td>
                    <span class="h4"><c:out value="${evento.id}"/></span>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.nome}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.descricao}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.categoria}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.data_inicio}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.data_termino}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="|${evento.nome_local}| Rua: ${evento.rua}, ${evento.numero} - Bairro: ${evento.bairro} - ${evento.cep}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.username_id}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${evento.nParticipantes}"/></p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

