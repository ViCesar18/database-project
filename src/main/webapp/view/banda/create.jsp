<%--
  Created by IntelliJ IDEA.
  User: claudio
  Date: 13/09/2020
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../view/include/head.jsp"%>
    <title>Rede Musical: Criação de banda</title>
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

        <a type="button" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/usuario/meu-perfil">Seu Perfil</a>

        <a type="button" class="btn btn-warning" href="${pageContext.servletContext.contextPath}/evento/create">Criar Evento</a>

        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/logout">Sair</a>
    </div>
</div>
<div class="container">
    <h1>Crie sua própria banda!</h1>
    <h1>Forneça as informações da sua banda.</h1>
    <form
            class="form"
            action="${pageContext.servletContext.contextPath}/banda/create"
            method="post"
            enctype="multipart/form-data"
    >
        <div class="form-group">
            <label for="inputNome">Nome</label>
            <input type="text" required class="form-control" id="inputNome" name="nome">
        </div>
        <div class="form-group">
            <label for="inputSigla">Sigla</label>
            <input type="text" required class="form-control" id="inputSigla" name="sigla">
        </div>

        <div class="form-group">
            <label for="inputImagem">Foto da banda</label>
            <input type="file" class="form-control-file" id="inputImagem" name="imagem">
        </div>

        <div class="form-group">
            <label for="inputGenero">Gênero</label>
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

        <button type="submit" class="btn btn-primary">Criar Banda</button>
    </form>
</div>

<%@include file="../../view/include/scripts.jsp"%>
</body>

