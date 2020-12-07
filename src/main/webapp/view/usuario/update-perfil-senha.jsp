<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 14/09/2020
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical: Editar Senha</title>
    </head>
    <body>
        <div class="container">
            <div style="margin: 10px">
                <img
                        src="${pageContext.request.contextPath}/assets/img/logo.png"
                        class="rounded-circle"
                        alt="Avatar"
                >
                <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/feed">Página Inicial</a>

                <a type="button" class="btn btn-success" href="${pageContext.servletContext.contextPath}/banda/create">Criar Banda</a>

                <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
            </div>
        </div>
        <div class="container">
            <h1>Edite sua senha.</h1>

            <form
                    action="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update-senha"
                    method="post"
                    class="form"
            >
                <input type="hidden" name="id" value="${usuario.getId()}">

                <div class="form-group">
                    <label for="inputSenha">Senha Atual</label>
                    <input type="password" required class="form-control" id="inputSenha" name="senha">
                </div>

                <div class="form-group">
                    <label for="inputNovaSenha">Nova Senha</label>
                    <input type="password" required class="form-control password-input" id="inputNovaSenha" name="novaSenha">
                </div>

                <div class="form-group">
                    <label for="inputConfirmacaoNovaSenha">Confirmação Nova Senha</label>
                    <input type="password" required class="form-control password-confirm" id="inputConfirmacaoNovaSenha" name="confirmacaoNovaSenha">
                    <p class="help-block" style="color: red"></p>
                </div>

                <button type="submit" class="btn btn-primary">Atualizar Senha</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Voltar</a>
            </form>
        </div>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
