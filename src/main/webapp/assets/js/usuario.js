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