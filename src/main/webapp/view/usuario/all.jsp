<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 15/09/2020
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical: Listar Usuários</title>
    </head>
    <body>
        <div class="container">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th class="col h4">ID</th>
                    <th class="col h4">Login</th>
                    <th class="col h4">E-mail</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="usuario" items="${requestScope.usuarios}">
                    <tr>
                        <td>
                            <span class="h4"><c:out value="${usuario.id}"/></span>
                        </td>
                        <td>
                            <p class="h4"><c:out value="${usuario.username}"/></p>
                        </td>

                        <td>
                            <p class="h4"><c:out value="${usuario.email}"/></p>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
