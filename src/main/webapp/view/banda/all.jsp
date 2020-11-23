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
    <title>Rede Musical: Listar Bandas</title>
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="col h4">ID</th>
            <th class="col h4">Sigla</th>
            <th class="col h4">Nome</th>
            <th class="col h4">Gênero</th>
            <th class="col h4">Id criador</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="banda" items="${requestScope.bandas}">
            <tr>
                <td>
                    <span class="h4"><c:out value="${banda.id}"/></span>
                </td>
                <td>
                    <p class="h4"><c:out value="${banda.sigla}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${banda.nome}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${banda.genero}"/></p>
                </td>
                <td>
                    <p class="h4"><c:out value="${banda.username_id}"/></p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

