<%--
  Created by IntelliJ IDEA.
  User: ViCesar18
  Date: 12/09/2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <%@include file="../../view/include/head.jsp"%>
        <title>Rede Musical: Cadastro</title>
    </head>
    <body>
        <div class="container">
            <div style="display: flex; flex-direction: row">
                <img
                        src="${pageContext.request.contextPath}/assets/img/logo.png"
                        class="rounded-circle"
                        alt="Avatar"
                >
                <h1>Crie sua conta!</h1>
            </div>
            <form
                class="form"
                action="${pageContext.servletContext.contextPath}/usuario/create"
                method="post"
                enctype="multipart/form-data"
            >
                <div class="form-group">
                    <label for="inputUsername"><b>Usuário</b></label>
                    <input type="text" required class="form-control" id="inputUsername" name="usuario">
                </div>
                <div class="form-group">
                    <label for="inputEmail"><b>E-mail</b></label>
                    <input type="email" required class="form-control" id="inputEmail" name="email">
                </div>

                <div class="form-group">
                    <label for="inputSenha"><b>Senha</b></label>
                    <input type="password" required class="form-control password-input" id="inputSenha" name="senha">
                </div>

                <div class="form-group">
                    <label for="inputConfirmacaoSenha"><b>Confirmação de Senha</b></label>
                    <input type="password" required class="form-control password-confirm" id="inputConfirmacaoSenha" name="confirmacaoSenha">
                    <p class="help-block" style="color: red"></p>
                </div>

                <div class="form-group">
                    <label for="inputNome"><b>Nome</b></label>
                    <input type="text" required class="form-control" id="inputNome" name="nome">
                </div>

                <div class="form-group">
                    <label for="inputSobrenome"><b>Sobrenome</b></label>
                    <input type="text" required class="form-control" id="inputSobrenome" name="sobrenome">
                </div>

                <div class="form-group">
                    <b>Sexo</b>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="inputSexo" id="inputMasculino" value="M" checked>
                        <label class="form-check-label" for="inputMasculino">
                            Masculino
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="inputSexo" id="inputFeminino" value="F">
                        <label class="form-check-label" for="inputFeminino">
                            Feminino
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="inputDtNascimento"><b>Data de Nascimento</b></label>
                    <input type="date" required class="form-control" id="inputDtNascimento" name="nascimento">
                </div>

                <div class="form-group">
                    <label for="inputImagem"><b>Foto de Perfil</b></label>
                    <input type="file" class="form-control-file" id="inputImagem" name="imagem">
                </div>

                <div class="form-group">
                    <label for="inputCidade"><b>Cidade</b></label>
                    <input type="text" required class="form-control" id="inputCidade" name="cidade">
                </div>

                <div class="form-group">
                    <label for="inputEstado"><b>Estado</b></label>
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
                    <label for="inputPais"><b>País</b></label>
                    <input type="text" required readonly class="form-control" id="inputPais" value="Brasil" name="pais">
                </div>

                <div class="form-group">
                    <label for="inputBanda"><b>Banda Favorita</b></label>
                    <select class="form-control" required id="inputBanda" name="banda">
                        <c:choose>
                            <c:when test="${not empty requestScope.bandas}">
                                <c:forEach var="banda" items="#{requestScope.bandas}">
                                    <option>${banda.nome} (${banda.sigla})</option>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <option>Nenhuma Banda Disponível</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>

                <div class="form-group">
                    <label for="inputMusica"><b>Música Favorita</b></label>
                    <input type="text" required class="form-control" id="inputMusica" name="musica">
                </div>

                <div class="form-group">
                    <label for="inputGenero"><b>Gênero Favorito</b></label>
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
                    <label for="inputInstrumento"><b>Instrumento Favorito</b></label>
                    <input type="text" required class="form-control" id="inputInstrumento" name="instrumento">
                </div>

                <button type="submit" class="btn btn-primary">Criar Conta</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/">Voltar</a>
            </form>
        </div>

        <%@include file="../../view/include/scripts.jsp"%>
    </body>
</html>
