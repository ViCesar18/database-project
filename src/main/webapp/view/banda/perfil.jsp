<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 23/11/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>
<%@ page import="model.Usuario" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Perfil da Banda</title>
</head>
<body>
<%
    Banda banda = (Banda)request.getAttribute("banda");
    Usuario usuarioLogado = (Usuario)session.getAttribute("usuario");
    System.out.println("hey" + usuarioLogado.getId() + "|" + banda.getUsername_id());
    //System.out.println(banda.getNome());
    String imgName = banda.getImagem();

    if(imgName == null || imgName.isBlank()) {
        imgName = "img/banda/default_avatar.png";
    }

    boolean criador;
    if (usuarioLogado.getId() == banda.getUsername_id()){
        request.setAttribute("criador", true);
    }
    else{
        request.setAttribute("criador", false);
    }

    request.setAttribute("imgName", imgName);
%>
<div class="container">
    <div class="text-center">
        <img
                src="${pageContext.request.contextPath}/assets/img/banda/${imgName}"
                class="rounded-circle"
                alt="Avatar"
                height="350"
                width="350"
        >

        <h1>${banda.getNome()} (${banda.getSigla()})</h1>
    </div>

    <div>
        <p><strong>Gênero:</strong> ${banda.getGenero()}</p>
    </div>
    <c:if test="${requestScope.criador}">
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil/delete?id=${banda.getId()}">Deletar banda</a>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil/update?id=${banda.getId()}">Editar banda</a>
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/banda/perfil/update-foto?id=${banda.getId()}">Alterar imagem</a>
    </c:if>
    <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
    </div>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

