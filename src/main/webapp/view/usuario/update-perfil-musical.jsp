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
            <h1>Edite seus dados.</h1>

            <form
                action="${pageContext.servletContext.contextPath}/usuario/perfil/update-musical"
                method="post"
            >
                <input type="hidden" name="id" value="${usuario.getId()}">

                <div class="form-group">
                    <label for="inputBanda">Banda Favorita</label>
                    <input type="text" required class="form-control" id="inputBanda" name="banda" value="${usuario.getBandaFavorita()}">
                </div>

                <div class="form-group">
                    <label for="inputMusica">Música Favorita</label>
                    <input type="text" required class="form-control" id="inputMusica" name="musica" value="${usuario.getMusicaFavorita()}">
                </div>

                <div class="form-group">
                    <label for="inputGenero">Gênero Favorito</label>
                    <select class="form-control" required id="inputGenero" name="genero">
                        <option id="Alternativo">Alternativo</option>
                        <option id="Axé">Axé</option>
                        <option id="Blues">Blues</option>
                        <option id="Bolero">Bolero</option>
                        <option id="Bossa Nova">Bossa Nova</option>
                        <option id="Brega">Brega</option>
                        <option id="Clássico">Clássico</option>
                        <option id="Country">Country</option>
                        <option id="Cuarteto">Cuarteto</option>
                        <option id="Cumbia">Cumbia</option>
                        <option id="Dance">Dance</option>
                        <option id="Disco">Disco</option>
                        <option id="Eletrônica">Eletrônica</option>
                        <option id="Emocore">Emocore</option>
                        <option id="Fado">Fado</option>
                        <option id="Folk">Folk</option>
                        <option id="Forró">Forró</option>
                        <option id="Funk">Funk</option>
                        <option id="Funk Internacional">Funk Internacional</option>
                        <option id="Gospel/Religioso">Gospel/Religioso</option>
                        <option id="Grunge">Grunge</option>
                        <option id="Guarânia">Guarânia</option>
                        <option id="Gótico">Gótico</option>
                        <option id="Hard Rock">Hard Rock</option>
                        <option id="Hardcore">Hardcore</option>
                        <option id="Heavy Metal">Heavy Metal</option>
                        <option id="Hip Hop/Rap">Hip Hop/Rap</option>
                        <option id="House">House</option>
                        <option id="Indie">Indie</option>
                        <option id="Industrial">Industrial</option>
                        <option id="Infantil">Infantil</option>
                        <option id="Instrumental">Instrumental</option>
                        <option id="J-Pop/J-Rock">J-Pop/J-Rock</option>
                        <option id="Jazz">Jazz</option>
                        <option id="Jovem Guarda">Jovem Guarda</option>
                        <option id="K-Pop/K-Rock">K-Pop/K-Rock</option>
                        <option id="MPB">MPB</option>
                        <option id="Mambo">Mambo</option>
                        <option id="Marchas/Hinos">Marchas/Hinos</option>
                        <option id="Mariachi">Mariachi</option>
                        <option id="Merengue">Merengue</option>
                        <option id="Música Andina">Música Andina</option>
                        <option id="New Age">New Age</option>
                        <option id="New Wave">New Wave</option>
                        <option id="Pagode">Pagode</option>
                        <option id="Pop">Pop</option>
                        <option id="Pop Rock">Pop Rock</option>
                        <option id="Post-Rock">Post-Rock</option>
                        <option id="Power-Pop">Power-Pop</option>
                        <option id="Psicodelia">Psicodelia</option>
                        <option id="Punk Rock">Punk Rock</option>
                        <option id="R&B">R&B</option>
                        <option id="Ranchera">Ranchera</option>
                        <option id="Reggae">Reggae</option>
                        <option id="Reggaeton">Reggaeton</option>
                        <option id="Regional">Regional</option>
                        <option id="Rock">Rock</option>
                        <option id="Rock Progressivo">Rock Progressivo</option>
                        <option id="Rockabilly">Rockabilly</option>
                        <option id="Romântico">Romântico</option>
                        <option id="Salsa">Salsa</option>
                        <option id="Samba">Samba</option>
                        <option id="Samba Enredo">Samba Enredo</option>
                        <option id="Sertanejo">Sertanejo</option>
                        <option id="Ska">Ska</option>
                        <option id="Soft Rock">Soft Rock</option>
                        <option id="Soul">Soul</option>
                        <option id="Surf Music">Surf Music</option>
                        <option id="Tango">Tango</option>
                        <option id="Tecnopop">Tecnopop</option>
                        <option id="Trova">Trova</option>
                        <option id="Velha Guarda">Velha Guarda</option>
                        <option id="World Music">World Music</option>
                        <option id="Zamba">Zamba</option>
                        <option id="Zouk">Zouk</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="inputInstrumento">Instrumento Favorito</label>
                    <input type="text" required class="form-control" id="inputInstrumento" name="instrumento" value="${usuario.getInstrumentoFavorito()}">
                </div>

                <button type="submit" class="btn btn-primary">Atualizar Dados</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/usuario/perfil">Voltar</a>
            </form>
        </div>

        <script>
            window.onload = function() {
                <% Usuario user = (Usuario) request.getAttribute("usuario"); %>
                var id = <%= user.getGeneroFavorito() %>

                var option = document.getElementById(id.value)

                option.setAttribute("selected", true)
            }
        </script>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
