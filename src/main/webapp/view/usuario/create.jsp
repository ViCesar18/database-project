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
                class="form"
                action="${pageContext.servletContext.contextPath}/usuario/create"
                method="post"
                enctype="multipart/form-data"
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
                    <input type="password" required class="form-control password-input" id="inputSenha" name="senha">
                </div>

                <div class="form-group">
                    <label for="inputConfirmacaoSenha">Confirmação de Senha</label>
                    <input type="password" required class="form-control password-confirm" id="inputConfirmacaoSenha" name="confirmacaoSenha">
                    <p class="help-block" style="color: red"></p>
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
                        <option>RN</option>
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
                    <label for="inputPais">País</label>
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
                    <select class="form-control" required id="inputGenero" name="genero">
                        <option>Alternativo</option>
                        <option>Axé</option>
                        <option>Blues</option>
                        <option>Bolero</option>
                        <option>Bossa Nova</option>
                        <option>Brega</option>
                        <option>Clássico</option>
                        <option>Country</option>
                        <option>Cuarteto</option>
                        <option>Cumbia</option>
                        <option>Dance</option>
                        <option>Disco</option>
                        <option>Eletrônica</option>
                        <option>Emocore</option>
                        <option>Fado</option>
                        <option>Folk</option>
                        <option>Forró</option>
                        <option>Funk</option>
                        <option>Funk Internacional</option>
                        <option>Gospel/Religioso</option>
                        <option>Grunge</option>
                        <option>Guarânia</option>
                        <option>Gótico</option>
                        <option>Hard Rock</option>
                        <option>Hardcore</option>
                        <option>Heavy Metal</option>
                        <option>Hip Hop/Rap</option>
                        <option>House</option>
                        <option>Indie</option>
                        <option>Industrial</option>
                        <option>Infantil</option>
                        <option>Instrumental</option>
                        <option>J-Pop/J-Rock</option>
                        <option>Jazz</option>
                        <option>Jovem Guarda</option>
                        <option>K-Pop/K-Rock</option>
                        <option>MPB</option>
                        <option>Mambo</option>
                        <option>Marchas/Hinos</option>
                        <option>Mariachi</option>
                        <option>Merengue</option>
                        <option>Música Andina</option>
                        <option>New Age</option>
                        <option>New Wave</option>
                        <option>Pagode</option>
                        <option>Pop</option>
                        <option>Pop Rock</option>
                        <option>Post-Rock</option>
                        <option>Power-Pop</option>
                        <option>Psicodelia</option>
                        <option>Punk Rock</option>
                        <option>R&B</option>
                        <option>Ranchera</option>
                        <option>Reggae</option>
                        <option>Reggaeton</option>
                        <option>Regional</option>
                        <option>Rock</option>
                        <option>Rock Progressivo</option>
                        <option>Rockabilly</option>
                        <option>Romântico</option>
                        <option>Salsa</option>
                        <option>Samba</option>
                        <option>Samba Enredo</option>
                        <option>Sertanejo</option>
                        <option>Ska</option>
                        <option>Soft Rock</option>
                        <option>Soul</option>
                        <option>Surf Music</option>
                        <option>Tango</option>
                        <option>Tecnopop</option>
                        <option>Trova</option>
                        <option>Velha Guarda</option>
                        <option>World Music</option>
                        <option>Zamba</option>
                        <option>Zouk</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="inputInstrumento">Instrumento Favorito</label>
                    <input type="text" required class="form-control" id="inputInstrumento" name="instrumento">
                </div>

                <button type="submit" class="btn btn-primary">Criar Conta</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/">Voltar</a>
            </form>
        </div>

        <%@include file="../../view/include/scripts.jsp"%>
    </body>
</html>
