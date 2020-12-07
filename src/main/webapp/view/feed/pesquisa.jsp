<%--
  Created by IntelliJ IDEA.
  User: vnces
  Date: 19/11/2020
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Resultado da Pesquisa</title>
</head>
<body>

<div class="container">
    <h1>Resultado da Pesquisa</h1>

    <c:if test="${requestScope.pesquisa.filtroUsuario}">
        <h2>Usuários Encontrados</h2>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Usuário</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Seguir</th>
                    <th scope="col">Acessar Perfil</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="pesquisa" items="${requestScope.pesquisas}">
                <c:if test="${pesquisa.idUsuario != null && pesquisa.idUsuario != sessionScope.usuario.id}">
                    <tr>
                        <td>
                           <c:out value="${pesquisa.usernameUsuario}"/>
                        </td>
                        <td>
                            <c:out value="${pesquisa.nomeUsuario}"/>
                        </td>
                        <td>
                            <c:if test="${!pesquisa.usuarioLogadoSegueUsuario}">
                                <button
                                        type="button"
                                        class="btn btn-success"
                                        onclick="seguirUsuario(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idUsuario})"
                                >
                                    Seguir
                                </button>
                            </c:if>
                            <c:if test="${pesquisa.usuarioLogadoSegueUsuario}">
                                <button
                                        type="button"
                                        class="btn btn-warning"
                                        onclick="pararSeguirUsuario(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idUsuario})"
                                >
                                    Deixar de Seguir
                                </button>
                            </c:if>
                        </td>
                        <td>
                            <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/perfil?idUsuario=${pesquisa.idUsuario}">Perfil</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${requestScope.pesquisa.filtroBanda}">
        <h2>Bandas Encontradas</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Sigla</th>
                <th scope="col">Nome</th>
                <th scope="col">Seguir</th>
                <th scope="col">Participar</th>
                <th scope="col">Acessar Banda</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pesquisa" items="${requestScope.pesquisas}">
                <c:if test="${pesquisa.idBanda != null}">
                    <tr>
                        <td>
                            <c:out value="${pesquisa.siglaBanda}"/>
                        </td>
                        <td>
                            <c:out value="${pesquisa.nomeBanda}"/>
                        </td>
                        <td>
                            <c:if test="${!pesquisa.usuarioLogadoSegueBanda}">
                                <button
                                        type="button"
                                        class="btn btn-success"
                                        onclick="seguirBanda(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idBanda})"
                                        ${sessionScope.usuario.id == pesquisa.idCriadorBanda ? 'disabled' : null}
                                >
                                    Seguir
                                </button>
                            </c:if>
                            <c:if test="${pesquisa.usuarioLogadoSegueBanda}">
                                <button
                                        type="button"
                                        class="btn btn-warning"
                                        onclick="pararSeguirBanda(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idBanda})"
                                >
                                    Deixar de Seguir
                                </button>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${pesquisa.usuarioLogadoParticipaBanda}">
                                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/parar-participar-banda?id=${pesquisa.idBanda}">Deixar de Participar da Banda</a>
                            </c:if>
                            <c:if test="${!pesquisa.usuarioLogadoParticipaBanda}">
                                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/participar-banda?id=${pesquisa.idBanda}">Participar da Banda</a>
                            </c:if>
                        </td>
                        <td>
                            <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/banda/perfil?id=${pesquisa.idBanda}">Perfil da Banda</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${requestScope.pesquisa.filtroEvento}">
        <h2>Eventos Encontrados</h2>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">Nome</th>
                <th scope="col">Participar</th>
                <th scope="col">Acessar Evento</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pesquisa" items="${requestScope.pesquisas}">
                <c:if test="${pesquisa.idEvento != null}">
                    <tr>
                        <td>
                            <c:out value="${pesquisa.nomeEvento}"/>
                        </td>
                        <td>
                            <c:if test="${!pesquisa.usuarioLogadoCompareceEvento}">
                                <button
                                        type="button"
                                        class="btn btn-success"
                                        onclick="comparecerEvento(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idEvento})"
                                >
                                    Marcar Presença
                                </button>
                            </c:if>
                            <c:if test="${pesquisa.usuarioLogadoCompareceEvento}">
                                <button
                                        type="button"
                                        class="btn btn-warning"
                                        onclick="pararComparecerEvento(this, '${pageContext.servletContext.contextPath}', ${sessionScope.usuario.id}, ${pesquisa.idEvento})"
                                >
                                    Desmarcar Presença
                                </button>
                            </c:if>
                        </td>
                        <td>
                            <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/evento/perfil?id=${pesquisa.idEvento}">Perfil do Evento</a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
</div>
<%@include file="../include/scripts.jsp"%>
</body>
</html>
