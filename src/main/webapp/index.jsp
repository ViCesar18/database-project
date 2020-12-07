<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 27/08/2020
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <%@include file="view/include/head.jsp"%>
        <title>Rede Musical</title>
    </head>
    <body>
        <div class="container">
            <div style="display: flex; flex-direction: row">
                <img
                        src="${pageContext.request.contextPath}/assets/img/logo.png"
                        class="rounded-circle"
                        alt="Avatar"
                >
                <div>
                    <br>
                    <h1>Seja Bem Vindo!</h1>
                    <h2>Entre ou Cadastre-se.</h2>
                </div>
            </div>
            <form
                action="${pageContext.servletContext.contextPath}/login"
                method="post"
            >
                <div class="form-group">
                    <label for="inputUser">Usuário</label>
                    <input type="text" class="form-control" id="inputUser" name="usuario">
                </div>
                <div class="form-group">
                    <label for="inputSenha">Senha</label>
                    <input type="password" class="form-control" id="inputSenha" name="senha">
                </div>

                <button type="submit" class="btn btn-primary">Entrar</button>
                <a class="btn btn-success" href="${pageContext.servletContext.contextPath}/usuario/create">Criar Conta</a>
            </form>
        </div>
        <%@include file="view/include/scripts.jsp"%>
    </body>
</html>
