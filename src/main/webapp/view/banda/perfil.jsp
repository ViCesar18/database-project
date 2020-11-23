<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 23/11/2020
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Banda" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Perfil da Banda</title>
</head>
<body>
<%
    Banda banda = (Banda)request.getAttribute("banda");
    System.out.println(banda.getNome());
    String imgName = banda.getImagem();

    if(imgName == null || imgName.isBlank()) {
        imgName = "img/banda/default_avatar.png";
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
        <a type="button" class="btn btn-danger" href="${pageContext.servletContext.contextPath}/feed">Voltar</a>
    </div>
</div>

<%@include file="../include/scripts.jsp"%>
</body>
</html>

