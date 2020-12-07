function curtirPost(botao, context, idUsuarioLogado, idPost){
    var url = 'http://www.localhost:8080' + context + '/curtir-post?idPost=' + idPost

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuarioLogado);

    botao.classList.remove('btn-primary')
    botao.classList.add('btn-warning')
    botao.onclick = function() { descurtirPost(botao, context, idUsuarioLogado, idPost) }
    botao.textContent = 'Descurtir'
}

function descurtirPost(botao, context, idUsuarioLogado, idPost) {
    var url = 'http://www.localhost:8080' + context + '/descurtir-post?idPost=' + idPost

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuarioLogado);

    botao.classList.remove('btn-warning')
    botao.classList.add('btn-primary')
    botao.onclick = function() { curtirPost(botao, context, idUsuarioLogado, idPost) }
    botao.textContent = 'Curtir'
}