function seguirBanda(botao, context, idUsuario, idBanda){
    var url = 'http://www.localhost:8080' + context + '/seguir-banda?idBanda=' + idBanda

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuario);

    botao.classList.remove('btn-success')
    botao.classList.add('btn-warning')
    botao.onclick = function() { pararSeguirBanda(botao, context, idUsuario, idBanda) }
    botao.textContent = 'Deixar de Seguir'
}

function pararSeguirBanda(botao, context, idUsuario, idBanda) {
    var url = 'http://www.localhost:8080' + context + '/parar-seguir-banda?idBanda=' + idBanda

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuario);

    botao.classList.remove('btn-warning')
    botao.classList.add('btn-success')
    botao.onclick = function() { seguirBanda(botao, context, idUsuario, idBanda) }
    botao.textContent = 'Seguir'
}