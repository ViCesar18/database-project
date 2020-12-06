package model;

import java.sql.Timestamp;

public class Comentario {
    private Integer id;
    private Timestamp dtPublicacao;
    private String textoComentario;
    private Integer usuarioId;
    private Integer postId;
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Timestamp dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
