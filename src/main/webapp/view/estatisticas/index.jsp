<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 19/04/2021
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Estatísticas</title>
</head>
<body>
<div class="container">
    <div style="display: flex; flex-direction: row">
        <img
                src="${pageContext.request.contextPath}/assets/img/logo.png"
                class="rounded-circle"
                alt="Avatar"
        >
        <div>
            <h1>Estatisticas</h1>
        </div>
    </div>
    <canvas id="myChart"></canvas>
</div>

<script>
    const labels = [
        'January',
        'February',
        'March',
        'April',
        'May',
        'June',
    ];
    const data = {
        labels: labels,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: [0, 10, 5, 2, 20, 30, 45],
        }]
    };

    const config = {
        type: 'bar',
        data,
        options: {}
    };

    var myChart = new Chart(
        document.getElementById('myChart'),
        config
    );
</script>



<%@include file="../include/scripts.jsp"%>
</body>
</html>
