$(document).on('focusout', '.password-input, .password-confirm', function (e) {
    var $form = $(this).closest("form");
    var $password = $form.find(".password-input");
    var $passwordConfirm = $form.find(".password-confirm");

    if($password.val().trim() == '') {
        return false;
    }

    if($password.val() !== $passwordConfirm.val()) {
        $password.closest('.form-group').addClass('has-error');
        $passwordConfirm.closest('.form-group').addClass('has-error');
        $passwordConfirm.next('p.help-block').html('As senhas devem ser iguais!');
        $form.find("button,input[type='submit']").prop('disabled', true);
    }
    else {
        $password.closest('.form-group').removeClass('has-error');
        $passwordConfirm.closest('.form-group').removeClass('has-error');
        $passwordConfirm.next('p.help-block').html('');
        $form.find("button, input[type='submit']").prop('disabled', false);
    }
});

function seguirUsuario(botao, context, idUsuarioLogado, idUsuarioSeguido){
    var url = 'http://www.localhost:8080' + context + '/seguir-usuario?idUsuario=' + idUsuarioSeguido

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuarioLogado);

    botao.classList.remove('btn-success')
    botao.classList.add('btn-warning')
    botao.onclick = function() { pararSeguirUsuario(botao, context, idUsuarioLogado, idUsuarioSeguido) }
    botao.textContent = 'Deixar de Seguir'
}

function pararSeguirUsuario(botao, context, idUsuarioLogado, idUsuarioSeguido) {
    var url = 'http://www.localhost:8080' + context + '/parar-seguir-usuario?idUsuario=' + idUsuarioSeguido

    var oReq = new XMLHttpRequest();
    oReq.open('post', url, true);
    oReq.send(idUsuarioLogado);

    botao.classList.remove('btn-warning')
    botao.classList.add('btn-success')
    botao.onclick = function() { seguirUsuario(botao, context, idUsuarioLogado, idUsuarioSeguido) }
    botao.textContent = 'Seguir'
}