<<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 24/11/2020
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Editar Evento</title>
</head>
<body>
<div class="container">
    <h1>Edite seu Evento.</h1>

    <form
            action="${pageContext.servletContext.contextPath}/evento/perfil/update"
            method="post"
    >
        <input type="hidden" name="id" value="${evento.getId()}">
        <input type="hidden" name="usuario_id" value="${evento.getUsername_id()}">

        <div class="form-group">
            <label for="inputNome">Nome</label>
            <input type="text" required class="form-control" id="inputNome" name="nome" value="${evento.getNome()}">
        </div>

        <div class="form-group">
            <label for="inputDescricao">Descrição</label>
            <input type="text" required class="form-control" id="inputDescricao" name="descricao" value="${evento.getDescricao()}">
        </div>

        <div class="form-group">
            <label for="inputNomeLocal">Local</label>
            <input type="text" required class="form-control" id="inputNomeLocal" name="nome_local" value="${evento.getNome_local()}">
        </div>

        <div class="form-group">
            <label for="inputRua">Rua</label>
            <input type="text" required class="form-control" id="inputRua" name="rua" value="${evento.getRua()}">
        </div>

        <div class="form-group">
            <label for="inputNumero">Número</label>
            <input type="text" required class="form-control" id="inputNumero" name="numero" value="${evento.getNumero()}">
        </div>

        <div class="form-group">
            <label for="inputBairro">Bairro</label>
            <input type="text" required class="form-control" id="inputBairro" name="bairro" value="${evento.getBairro()}">
        </div>

        <div class="form-group">
            <label for="inputCep">CEP</label>
            <input type="text" required class="form-control" id="inputCep" name="cep" value="${evento.getCep()}">
        </div>

        <div class="form-group">
            <label for="inputDtInicio">Data de Início</label>
            <input type="datetime-local" required class="form-control" id="inputDtInicio" name="data_inicio">
        </div>

        <div class="form-group">
            <label for="inputDtTermino">Data de Término</label>
            <input type="datetime-local" required class="form-control" id="inputDtTermino" name="data_termino">
        </div>

        <div class="form-group">
            <label for="inputCategoria">Categoria</label>
            <select class="form-control" required id="inputCategoria" name="categoria" value="${evento.getCategoria()}">
                <option>Show</option>
                <option>Concerto</option>
                <option>Festival</option>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Atualizar Evento</button>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/evento/perfil">Voltar</a>
    </form>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>


