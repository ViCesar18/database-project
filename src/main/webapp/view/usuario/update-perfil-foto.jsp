<%@ page import="model.Usuario" %><%--
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
        <title>Rede Musical: Editar Foto</title>
    </head>
    <body>
        <%
            Usuario user = (Usuario)request.getAttribute("usuario");
            String imgName = user.getImagem();

            if(imgName == null || imgName.isBlank()) {
                imgName = "default_avatar.png";
            }

            request.setAttribute("imgName", imgName);
        %>
        <div class="container">
            <div class="text-center">
                <h1>Edite sua foto.</h1>

                <img
                    src="${pageContext.request.contextPath}/assets/img/usuario/${imgName}"
                    class="rounded-circle"
                    alt="Avatar"
                    height="350"
                    width="350"
                >
            </div>

            <form
                action="${pageContext.request.contextPath}/usuario/perfil/update-foto"
                method="post"
                enctype="multipart/form-data"
            >
                <input type="hidden" name="id" value="${usuario.getId()}">

                <div class="form-group">
                    <label for="inputImagem">Foto de Perfil</label>
                    <input type="file" class="form-control-file" id="inputImagem" name="imagem">
                </div>

                <button type="submit" class="btn btn-primary">Atualizar Foto</button>
                <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/usuario/perfil">Voltar</a>
            </form>
        </div>

        <%@include file="../include/scripts.jsp"%>
    </body>
</html>
