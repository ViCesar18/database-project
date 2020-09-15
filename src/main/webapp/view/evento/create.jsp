<%--
  Created by IntelliJ IDEA.
  User: claudio
  Date: 14/09/2020
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../view/include/head.jsp"%>
    <title>Rede Musical: Criação de eventos</title>
</head>
<body>
<div class="container">
    <h1>Criação de eventos!</h1>
    <h1>Forneça as informações do evento.</h1>
    <form
    class="form"
    action="${pageContext.servletContext.contextPath}/evento/create"
    method="post"
    >

        <div class="form-group">
            <label for="inputNome">Nome</label>
            <input type="text" required class="form-control" id="inputNome" name="nome">
        </div>

        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <input type="text" required class="form-control" id="inputDescricao" name="descricao">
        </div>

        <div class="form-group">
            <label for="inputDtInicio">Data de Ínicio</label>
            <input type="datetime-local" required class="form-control" id="inputDtInicio" name="data_inicio">
        </div>

        <div class="form-group">
            <label for="inputDtTermino">Data de Término</label>
            <input type="datetime-local" required class="form-control" id="inputDtTermino" name="data_termino">
        </div>

        <div class="form-group">
            <label for="inputCategoria">Categoria</label>
            <select class="form-control" required id="inputCategoria" name="categoria">
                <option>Show</option>
                <option>Concerto</option>
                <option>Festival</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Criar Evento</button>
    </form>
</div>

<%@include file="../../view/include/scripts.jsp"%>
</body>
</html>


