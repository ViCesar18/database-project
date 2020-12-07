function comparecerEvento(botao, context, idUsuario, idEvento){
    var url = 'http://www.localhost:8080' + context + '/comparecer-evento?idEvento=' + idEvento

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuario);

    botao.classList.remove('btn-success')
    botao.classList.add('btn-warning')
    botao.onclick = function () { pararComparecerEvento(botao, context, idUsuario, idEvento)}
    botao.textContent = 'Desmarcar Presença'
}

function pararComparecerEvento(botao, context, idUsuario, idEvento){
    var url = 'http://www.localhost:8080' + context + '/parar-comparecer-evento?idEvento=' + idEvento

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuario);

    botao.classList.remove('btn-warning')
    botao.classList.add('btn-success')
    botao.onclick = function () { comparecerEvento(botao, context, idUsuario, idEvento)}
    botao.textContent = 'Marcar Presença'
}