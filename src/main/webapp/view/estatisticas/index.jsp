<%--
  Created by IntelliJ IDEA.
  User: claud
  Date: 19/04/2021
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../include/head.jsp"%>
    <title>Rede Musical: Estatisticas</title>
    <meta charset="UTF-8">
</head>
<body>
<div style="display: flex; flex-direction: column; padding: 50px">
    <div style="display: flex; flex-direction: row; align-items: center">
        <img
                src="${pageContext.request.contextPath}/assets/img/logo.png"
                class="rounded-circle"
                alt="Avatar"
        >
        <h1>Estatisticas</h1>
    </div>

    <div style="display: flex; justify-content: center; margin-top: 100px; margin-bottom: 20px">
        <h1>INTERAÇÕES</h1>
    </div>

    <div style="align-self: center; height: 10%; width: 80%">
        <canvas id="graficoInteracoes"></canvas>
    </div>

    <div style="display: flex; justify-content: center">
        <h2>Total de Interacoes: ${numeroInteracoes}</h2>
    </div>

    <div style="display: flex; justify-content: center; margin-top: 100px; margin-bottom: 20px">
        <h1>POSTS EM ALTA</h1>
    </div>

    <div style="display: flex; flex-direction: row;">
        <div id="postLikes">
            <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center">
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex; flex-direction: row; align-items: center">
                        <img
                                src="${pageContext.request.contextPath}/assets/img/usuario/${postLikes.usuario.imagem != null ? postLikes.usuario.imagem : "default_avatar.png"}"
                                class="rounded-circle"
                                alt="Avatar"
                                height="50"
                                width="50"
                                style="object-fit: cover"
                        >
                        <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                            <p style="height: 5px"><strong>${postLikes.usuario.pNome} ${postLikes.usuario.sNome}</strong></p>
                            <p style="height: 5px;"><i>${postLikes.dtPublicacao}</i></p>
                        </div>
                    </div>
                    <c:if test="${not empty postLikes.banda}">
                        <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20px">
                            <img
                                    src="${pageContext.request.contextPath}/assets/img/banda/${postLikes.banda.imagem != null ? postLikes.banda.imagem : "default_avatar.png"}"
                                    class="rounded-circle"
                                    alt="Avatar"
                                    height="50"
                                    width="50"
                                    style="object-fit: cover"
                            >
                            <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                <p style="height: 5px"><strong>${postLikes.banda.nome} (${postLikes.banda.sigla})</strong></p>
                                <p style="height: 5px;"><i>${postLikes.dtPublicacao}</i></p>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div>
                    <p style="color: rgb(255, 99, 132)"><i>Post Mais Curtido</i></p>
                </div>
            </div>
            <div style="margin-top: 20px">
                <p>${postLikes.textoPost}</p>
                <c:if test="${postLikes.imagem != null}">
                    <img
                            src="${pageContext.request.contextPath}/assets/img/post/${postLikes.imagem}"
                            style="width: 100%; height: 75%; object-fit: contain"
                    >
                </c:if>
                <p style="margin-top: 20px">
                    <strong>Curtidas:</strong> ${postLikes.nCurtidas} <strong>Comentarios:</strong> ${postLikes.nComentarios} <strong>Compartilhamento:</strong> ${postLikes.nCompartilhamentos}
                </p>
            </div>
        </div>

        <div id="postComentario" style="padding-left: 20px; padding-right: 20px">
            <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center">
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex; flex-direction: row; align-items: center">
                        <img
                                src="${pageContext.request.contextPath}/assets/img/usuario/${postComentarios.usuario.imagem != null ? postComentarios.usuario.imagem : "default_avatar.png"}"
                                class="rounded-circle"
                                alt="Avatar"
                                height="50"
                                width="50"
                                style="object-fit: cover"
                        >
                        <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                            <p style="height: 5px"><strong>${postComentarios.usuario.pNome} ${postComentarios.usuario.sNome}</strong></p>
                            <p style="height: 5px;"><i>${postComentarios.dtPublicacao}</i></p>
                        </div>
                    </div>
                    <c:if test="${not empty postComentarios.banda}">
                        <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20px">
                            <img
                                    src="${pageContext.request.contextPath}/assets/img/banda/${postComentarios.banda.imagem != null ? postComentarios.banda.imagem : "default_avatar.png"}"
                                    class="rounded-circle"
                                    alt="Avatar"
                                    height="50"
                                    width="50"
                                    style="object-fit: cover"
                            >
                            <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                <p style="height: 5px"><strong>${postComentarios.banda.nome} (${postComentarios.banda.sigla})</strong></p>
                                <p style="height: 5px;"><i>${postComentarios.dtPublicacao}</i></p>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div>
                    <p style="color: rgb(255, 99, 132)"><i>Post Mais Comentado</i></p>
                </div>
            </div>
            <div style="margin-top: 20px">
                <p>${postComentarios.textoPost}</p>
                <c:if test="${postComentarios.imagem != null}">
                    <img
                            src="${pageContext.request.contextPath}/assets/img/post/${postLikes.imagem}"
                            style="width: 100%; height: 75%; object-fit: contain"
                    >
                </c:if>
                <p style="margin-top: 20px">
                    <strong>Curtidas:</strong> ${postComentarios.nCurtidas} <strong>Comentarios:</strong> ${postComentarios.nComentarios} <strong>Compartilhamento:</strong> ${postComentarios.nCompartilhamentos}
                </p>
            </div>
        </div>

        <div id="postCompartilhamento">
            <div style="display: flex; flex-direction: row; justify-content: space-between; align-items: center">
                <div style="display: flex; flex-direction: row">
                    <div style="display: flex; flex-direction: row; align-items: center">
                        <img
                                src="${pageContext.request.contextPath}/assets/img/usuario/${postCompartilhamentos.usuario.imagem != null ? postCompartilhamentos.usuario.imagem : "default_avatar.png"}"
                                class="rounded-circle"
                                alt="Avatar"
                                height="50"
                                width="50"
                                style="object-fit: cover"
                        >
                        <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                            <p style="height: 5px"><strong>${postCompartilhamentos.usuario.pNome} ${postCompartilhamentos.usuario.sNome}</strong></p>
                            <p style="height: 5px;"><i>${postCompartilhamentos.dtPublicacao}</i></p>
                        </div>
                    </div>
                    <c:if test="${not empty postCompartilhamentos.banda}">
                        <div style="display: flex; flex-direction: row; align-items: center; margin-left: 20px">
                            <img
                                    src="${pageContext.request.contextPath}/assets/img/banda/${postCompartilhamentos.banda.imagem != null ? postCompartilhamentos.banda.imagem : "default_avatar.png"}"
                                    class="rounded-circle"
                                    alt="Avatar"
                                    height="50"
                                    width="50"
                                    style="object-fit: cover"
                            >
                            <div style="display: flex; flex-direction: column; justify-content: center; margin-left: 15px">
                                <p style="height: 5px"><strong>${postCompartilhamentos.banda.nome} (${postCompartilhamentos.banda.sigla})</strong></p>
                                <p style="height: 5px;"><i>${postCompartilhamentos.dtPublicacao}</i></p>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div>
                    <p style="color: rgb(255, 99, 132)"><i>Post Mais Compartilhado</i></p>
                </div>
            </div>
            <div style="margin-top: 20px">
                <p>${postCompartilhamentos.textoPost}</p>
                <c:if test="${postCompartilhamentos.imagem != null}">
                    <img
                            src="${pageContext.request.contextPath}/assets/img/post/${postCompartilhamentos.imagem}"
                            style="width: 100%; height: 75%; object-fit: contain"
                    >
                </c:if>
                <p style="margin-top: 20px">
                    <strong>Curtidas:</strong> ${postCompartilhamentos.nCurtidas} <strong>Comentarios:</strong> ${postCompartilhamentos.nComentarios} <strong>Compartilhamento:</strong> ${postCompartilhamentos.nCompartilhamentos}
                </p>
            </div>
        </div>
    </div>

    <div style="display: flex; justify-content: center; margin-top: 100px; margin-bottom: 20px">
        <h1>INSTRUMENTOS MAIS TOCADOS</h1>
    </div>

    <div style="display: flex; flex-direction: row; justify-content: space-evenly">
        <div style="text-align: center; height: 10%; width: 30%;">
            <h2>Mulheres</h2>
            <canvas id="graficoInstrumentosMulheres"></canvas>
        </div>

        <div style="text-align: center; height: 10%; width: 30%;">
            <h2>Homens</h2>
            <canvas id="graficoInstrumentosHomens"></canvas>
        </div>
    </div>

    <div style="display: flex; justify-content: center; margin-top: 100px; margin-bottom: 20px">
        <h1>GENEROS MAIS CURTIDOS</h1>
    </div>

    <div style="display: flex; flex-direction: row; justify-content: space-evenly">
        <div style="text-align: center; height: 10%; width: 30%;">
            <h2>Mulheres</h2>
            <canvas id="graficoGeneroMulheres"></canvas>
        </div>

        <div style="text-align: center; height: 10%; width: 30%;">
            <h2>Homens</h2>
            <canvas id="graficoGeneroHomens"></canvas>
        </div>
    </div>

    <div style="display: flex; justify-content: center; margin-top: 100px; margin-bottom: 20px">
        <h1>GERAÇÕES MAIS FREQUENTES</h1>
    </div>

    <div style="align-self: center; height: 10%; width: 30%">
        <canvas id="graficoIdadesFrequentes"></canvas>
    </div>

    <div style="display: flex; flex-direction: column; align-items: center; margin-top: 100px; margin-bottom: 20px">
        <h1>A geração mais ativa na rede é a <b style="color: rgb(255, 99, 132)">${geracaoMaisAtiva == 0 ? 'Geração Boomer' : ''} ${geracaoMaisAtiva == 1 ? 'Geração X' : ''} ${geracaoMaisAtiva == 2 ? 'Geração Millennials' : ''} ${geracaoMaisAtiva == 3 ? 'Geração Z' : ''}</b></h1>
        <p>Com <b style="color: rgb(255, 99, 132)">${geracaoMaisAtiva == 0 ? geracaoBoomer : ''} ${geracaoMaisAtiva == 1 ? geracaoX : ''} ${geracaoMaisAtiva == 2 ? geracaoMillennials : ''} ${geracaoMaisAtiva == 3 ? geracaoZ : ''} %</b> de atividade na rede</p>
    </div>

    <div style="display: flex; justify-content: center">
        <table class="table" style="width: 50%">
            <thead>
            <tr>
                <th scope="col">Geração</th>
                <th scope="col">%</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Geração Boomer</td>
                <td>${geracaoBoomer}</td>
            </tr>
            <tr>
                <td>Geração X</td>
                <td>${geracaoX}</td>
            </tr>
            <tr>
                <td>Geração Millennials</td>
                <td>${geracaoMillennials}</td>
            </tr>
            <tr>
                <td>Geração Z</td>
                <td>${geracaoZ}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
    const labelsInteracoes = [
        'Likes',
        'Comentarios',
        'Compartilhamentos'
    ];
    const dataInteracoes = {
        labels: labelsInteracoes,
        datasets: [{
            label: 'Interacoes',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: [${numeroLikes}, ${numeroComentarios}, ${numeroCompartilhamentos}],
        }]
    };

    const configInteracoes = {
        type: 'bar',
        data: dataInteracoes,
        options: {}
    };

    var graficoInteracoes = new Chart(
        document.getElementById('graficoInteracoes'),
        configInteracoes
    );
</script>

<script>
    const labelsInstrumentosMulheres = [
      '${instrumentosMulheres[0].instrumento}',
      '${instrumentosMulheres[1].instrumento}',
      '${instrumentosMulheres[2].instrumento}',
      '${instrumentosMulheres[3].instrumento}',
      '${instrumentosMulheres[4].instrumento}',
      '${instrumentosMulheres[5].instrumento}'
    ];

    const dataInstrumentosMulheres = {
      labels: labelsInstrumentosMulheres,
      datasets: [{
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(255, 205, 86)',
          'rgb(236,64,64)',
          'rgb(160,74,234)',
          'rgb(151,243,74)'
        ],
        data: [
          ${instrumentosMulheres[0].frequencia},
          ${instrumentosMulheres[1].frequencia},
          ${instrumentosMulheres[2].frequencia},
          ${instrumentosMulheres[3].frequencia},
          ${instrumentosMulheres[4].frequencia},
          ${instrumentosMulheres[5].frequencia}
        ],
      }]
    };

    const configInstrumentosMulheres = {
      type: 'pie',
      data: dataInstrumentosMulheres,
      options: {}
    };

    graficoInstrumentosMulheres = new Chart(
        document.getElementById('graficoInstrumentosMulheres'),
        configInstrumentosMulheres
    );
</script>

<script>
  const labelsInstrumentosHomens = [
    '${instrumentosHomens[0].instrumento}',
    '${instrumentosHomens[1].instrumento}',
    '${instrumentosHomens[2].instrumento}',
    '${instrumentosHomens[3].instrumento}',
    '${instrumentosHomens[4].instrumento}',
    '${instrumentosHomens[5].instrumento}'
  ];

  const dataInstrumentosHomens = {
    labels: labelsInstrumentosHomens,
    datasets: [{
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)',
        'rgb(236,64,64)',
        'rgb(160,74,234)',
        'rgb(151,243,74)'
      ],
      data: [
        ${instrumentosHomens[0].frequencia},
        ${instrumentosHomens[1].frequencia},
        ${instrumentosHomens[2].frequencia},
        ${instrumentosHomens[3].frequencia},
        ${instrumentosHomens[4].frequencia},
        ${instrumentosHomens[5].frequencia}
      ],
    }]
  };

  const configInstrumentosHomens = {
    type: 'pie',
    data: dataInstrumentosHomens,
    options: {}
  };

  graficoInstrumentosHomens = new Chart(
      document.getElementById('graficoInstrumentosHomens'),
      configInstrumentosHomens
  );
</script>

<script>
  const labelsGeneroMulheres = [
    '${generosFavoritosMulheres[0].generoFavorito}',
    '${generosFavoritosMulheres[1].generoFavorito}',
    '${generosFavoritosMulheres[2].generoFavorito}',
    '${generosFavoritosMulheres[3].generoFavorito}',
    '${generosFavoritosMulheres[4].generoFavorito}',
    '${generosFavoritosMulheres[5].generoFavorito}',
    '${generosFavoritosMulheres[6].generoFavorito}',
    '${generosFavoritosMulheres[7].generoFavorito}',
    '${generosFavoritosMulheres[8].generoFavorito}',
    '${generosFavoritosMulheres[9].generoFavorito}'
  ];

  const dataGeneroMulheres = {
    labels: labelsGeneroMulheres,
    datasets: [{
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)',
        'rgb(236,64,64)',
        'rgb(74,91,243)',
        'rgb(160,74,234)',
        'rgb(151,243,74)',
        'rgb(74,175,243)',
        'rgb(243,122,74)',
        'rgb(74,243,184)'
      ],
      data: [
        ${generosFavoritosMulheres[0].frequencia},
        ${generosFavoritosMulheres[1].frequencia},
        ${generosFavoritosMulheres[2].frequencia},
        ${generosFavoritosMulheres[3].frequencia},
        ${generosFavoritosMulheres[4].frequencia},
        ${generosFavoritosMulheres[5].frequencia},
        ${generosFavoritosMulheres[6].frequencia},
        ${generosFavoritosMulheres[7].frequencia},
        ${generosFavoritosMulheres[8].frequencia},
        ${generosFavoritosMulheres[9].frequencia}
      ],
    }]
  };

  const configGeneroMulheres = {
    type: 'pie',
    data: dataGeneroMulheres,
    options: {}
  };

  graficoGeneroMulheres = new Chart(
      document.getElementById('graficoGeneroMulheres'),
      configGeneroMulheres
  );
</script>

<script>
  const labelsGeneroHomens = [
    '${generosPreferidosHomens[0].generoFavorito}',
    '${generosPreferidosHomens[1].generoFavorito}',
    '${generosPreferidosHomens[2].generoFavorito}',
    '${generosPreferidosHomens[3].generoFavorito}',
    '${generosPreferidosHomens[4].generoFavorito}',
    '${generosPreferidosHomens[5].generoFavorito}',
    '${generosPreferidosHomens[6].generoFavorito}',
    '${generosPreferidosHomens[7].generoFavorito}',
    '${generosPreferidosHomens[8].generoFavorito}',
    '${generosPreferidosHomens[9].generoFavorito}'
  ];

  const dataGeneroHomens = {
    labels: labelsGeneroHomens,
    datasets: [{
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)',
        'rgb(236,64,64)',
        'rgb(74,91,243)',
        'rgb(160,74,234)',
        'rgb(151,243,74)',
        'rgb(74,175,243)',
        'rgb(243,122,74)',
        'rgb(74,243,184)'
      ],
      data: [
        ${generosPreferidosHomens[0].frequencia},
        ${generosPreferidosHomens[1].frequencia},
        ${generosPreferidosHomens[2].frequencia},
        ${generosPreferidosHomens[3].frequencia},
        ${generosPreferidosHomens[4].frequencia},
        ${generosPreferidosHomens[5].frequencia},
        ${generosPreferidosHomens[6].frequencia},
        ${generosPreferidosHomens[7].frequencia},
        ${generosPreferidosHomens[8].frequencia},
        ${generosPreferidosHomens[9].frequencia}
      ],
    }]
  };

  const configGeneroHomens = {
    type: 'pie',
    data: dataGeneroHomens,
    options: {}
  };

  graficoGeneroHomens = new Chart(
      document.getElementById('graficoGeneroHomens'),
      configGeneroHomens
  );
</script>

<script>
    const labelsIdadesFrequentes = [
        'Geracao Boomer (maior que 60)',
        'Geracao X (40-59 anos)',
        'Geracao Millennials (25-39 anos)',
        'Geracao Z (10-24 anos)'
    ];

    const dataIdadesFrequentes = {
        labels: labelsIdadesFrequentes,
        datasets: [{
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54,162,235)',
                'rgb(111,212,56)',
                'rgb(235,211,54)',
            ],
            data: [
                ${frequenciaGeracoes.geracao_boomer_count},
                ${frequenciaGeracoes.geracao_x_count},
                ${frequenciaGeracoes.geracao_millennials_count},
                ${frequenciaGeracoes.geracao_z_count},
            ],
        }]
    };

    const configIdadesFrequentes = {
        type: 'doughnut',
        data: dataIdadesFrequentes,
        options: {
            responsive:true
        }
    };

    graficoIdadesFrequentes = new Chart(
        document.getElementById('graficoIdadesFrequentes'),
        configIdadesFrequentes
    );
</script>

<%@include file="../include/scripts.jsp"%>
</body>
</html>
