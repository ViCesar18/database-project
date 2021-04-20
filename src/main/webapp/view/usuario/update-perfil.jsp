<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 14/09/2020
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Usuario" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../include/head.jsp"%>
        <title>Rede Musical: Editar Perfil</title>
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
            <h1>Edite seus dados pessoais.</h1>

            <form
                action="${pageContext.servletContext.contextPath}/usuario/meu-perfil/update"
                method="post"
            >
                <input type="hidden" name="id" value="${usuario.getId()}">

                <div class="form-group">
                    <label for="inputUsuario">Usuário</label>
                    <input type="text" required class="form-control" id="inputUsuario" name="username" value="${usuario.getUsername()}">
                </div>

                <div class="form-group">
                    <label for="inputEmail">E-mail</label>
                    <input type="email" required class="form-control" id="inputEmail" name="email" value="${usuario.getEmail()}">
                </div>

                <div class="form-group">
                    <label for="inputNome">Nome</label>
                    <input type="text" required class="form-control" id="inputNome" name="nome" value="${usuario.getpNome()}">
                </div>

                <div class="form-group">
                    <label for="inputSobrenome">Sobrenome</label>
                    <input type="text" required class="form-control" id="inputSobrenome" name="sobrenome" value="${usuario.getsNome()}">
                </div>

                <div class="form-group">
                    Sexo
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="inputSexo" id="inputMasculino" value="M" ${usuario.getSexo().equals("M") ? "checked" : null}>
                        <label class="form-check-label" for="inputMasculino">
                            Masculino
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="inputSexo" id="inputFemenino" value="F" ${usuario.getSexo().equals("F") ? "checked" : null}>
                        <label class="form-check-label" for="inputFemenino">
                            Femenino
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDtNascimento">Data de Nascimento</label>
                    <input type="date" required class="form-control" id="inputDtNascimento" name="nascimento" value="${usuario.getDtNascimento()}">
                </div>

                <div class="form-group">
                    <label for="inputCidade">Cidade</label>
                    <input type="text" required class="form-control" id="inputCidade" name="cidade" value="${usuario.getCidade()}">
                </div>

                <div class="form-group">
                    <label for="inputEstado">Estado</label>
                    <select class="form-control" required id="inputEstado" name="estado">
                        <option id="AC">AC</option>
                        <option id="AL">AL</option>
                        <option id="AP">AP</option>
                        <option id="AM">AM</option>
                        <option id="BA">BA</option>
                        <option id="CE">CE</option>
                        <option id="DF">DF</option>
                        <option id="ES">ES</option>
                        <option id="GO">GO</option>
                        <option id="MA">MA</option>
                        <option id="MT">MT</option>
                        <option id="MS">MS</option>
                        <option id="MG">MG</option>
                        <option id="PA">PA</option>
                        <option id="PB">PB</option>
                        <option id="PR">PR</option>
                        <option id="PE">PE</option>
                        <option id="PI">PI</option>
                        <option id="RJ">RJ</option>
                        <option id="RN">RN</option>
                        <option id="RS">RS</option>
                        <option id="RO">RO</option>
                        <option id="RR">RR</option>
                        <option id="SC">SC</option>
                        <option id="SP">SP</option>
                        <option id="SE">SE</option>
                        <option id="TO">TO</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="inputPais">País</label>
                    <input type="text" required readonly class="form-control" id="inputPais" name="pais" value="${usuario.getPais()}">
                </div>

                <button type="submit" class="btn btn-primary">Atualizar Dados Pessoais</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Voltar</a>
            </form>
        </div>

        <script>
            window.onload = function() {
                <% Usuario user = (Usuario) request.getAttribute("usuario"); %>
                var id = <%= user.getEstado() %>

                var option = document.getElementById(id.value)

                option.setAttribute("selected", true)
            }
        </script>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
