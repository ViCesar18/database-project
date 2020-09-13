<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 12/09/2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../../view/include/head.jsp"%>
        <title>Rede Musical: Cadastro</title>
    </head>
    <body>
        <div class="container">
            <h1>Crie sua conta!</h1>
            <form
                action="${pageContext.servletContext.contextPath}/usuario/create"
                method="post"
            >
                <div class="form-group">
                    <label for="inputUsername">Usuário</label>
                    <input type="text" required class="form-control" id="inputUsername" name="usuario">
                </div>
                <div class="form-group">
                    <label for="inputEmail">E-mail</label>
                    <input type="email" required class="form-control" id="inputEmail" name="email">
                </div>

                <div class="form-group">
                    <label for="inputSenha">Senha</label>
                    <input type="password" required class="form-control" id="inputSenha" name="senha">
                </div>

                <div class="form-group">
                    <label for="inputConfirmacaoSenha">Confimação de Senha</label>
                    <input type="password" class="form-control" id="inputConfirmacaoSenha" name="confirmacaoSenha">
                </div>

                <div class="form-group">
                    <label for="inputNome">Nome</label>
                    <input type="text" required class="form-control" id="inputNome" name="nome">
                </div>

                <div class="form-group">
                    <label for="inputSobrenome">Sobrenome</label>
                    <input type="text" required class="form-control" id="inputSobrenome" name="sobrenome">
                </div>

                <div class="form-group">
                    <label for="inputDtNascimento">Data de Nascimento</label>
                    <input type="date" required class="form-control" id="inputDtNascimento" name="nascimento">
                </div>

                <div class="form-group">
                    <label for="inputImagem">Foto de Perfil</label>
                    <input type="file" class="form-control-file" id="inputImagem" name="imagem">
                </div>

                <div class="form-group">
                    <label for="inputCidade">Cidade</label>
                    <input type="text" required class="form-control" id="inputCidade" name="cidade">
                </div>

                <div class="form-group">
                    <label for="inputEstado">Estado</label>
                    <select class="form-control" required id="inputEstado" name="estado">
                        <option>AC</option>
                        <option>AL</option>
                        <option>AP</option>
                        <option>AM</option>
                        <option>BA</option>
                        <option>CE</option>
                        <option>DF</option>
                        <option>ES</option>
                        <option>GO</option>
                        <option>MA</option>
                        <option>MT</option>
                        <option>MS</option>
                        <option>MG</option>
                        <option>PA</option>
                        <option>PB</option>
                        <option>PR</option>
                        <option>PE</option>
                        <option>PI</option>
                        <option>RJ</option>
                        <option>RJ</option>
                        <option>RS</option>
                        <option>RO</option>
                        <option>RR</option>
                        <option>SC</option>
                        <option>SP</option>
                        <option>SE</option>
                        <option>TO</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="inputPais">Pais</label>
                    <input type="text" required readonly class="form-control" id="inputPais" value="Brasil" name="pais">
                </div>

                <div class="form-group">
                    <label for="inputBanda">Banda Favorita</label>
                    <input type="text" required class="form-control" id="inputBanda" name="banda">
                </div>

                <div class="form-group">
                    <label for="inputMusica">Música Favorita</label>
                    <input type="text" required class="form-control" id="inputMusica" name="musica">
                </div>

                <div class="form-group">
                    <label for="inputGenero">Gênero Favorito</label>
                    <input type="text" required class="form-control" id="inputGenero" name="genero">
                </div>

                <div class="form-group">
                    <label for="inputInstrumento">Instrumento Favorita</label>
                    <input type="text" required class="form-control" id="inputInstrumento" name="instrumento">
                </div>

                <button type="submit" class="btn btn-primary">Criar Conta</button>
            </form>
        </div>

        <%@include file="../../view/include/scripts.jsp"%>
    </body>
</html>
