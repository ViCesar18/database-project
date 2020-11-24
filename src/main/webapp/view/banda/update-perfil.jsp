<<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 24/11/2020
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Editar Banda</title>
</head>
<body>
<div class="container">
    <h1>Edite sua Banda.</h1>

    <form
            action="${pageContext.servletContext.contextPath}/banda/perfil/update"
            method="post"
    >
        <input type="hidden" name="id" value="${banda.getId()}">

        <div class="form-group">
            <label for="inputNome">Nome</label>
            <input type="text" required class="form-control" id="inputNome" name="nome" value="${banda.getNome()}">
        </div>

        <div class="form-group">
            <label for="inputSigla">Sigla</label>
            <input type="text" required class="form-control" id="inputSigla" name="sigla" value="${banda.getSigla()}">
        </div>

        <div class="form-group">
            <label for="inputGenero">Gênero</label>
            <select class="form-control" required id="inputGenero" name="genero" value="${banda.getGenero()}">
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


        <button type="submit" class="btn btn-primary">Atualizar Banda</button>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil">Voltar</a>
    </form>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>


