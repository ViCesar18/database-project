package model;

import java.sql.Timestamp;

public class Post {
    private Integer id;
    private String textoPost;
    private String imagem;
    private Timestamp dtPublicacao;
    private Integer nLikes;
    private Integer nComentarios;
    private Integer nCompartilhamentos;
    private Integer usuarioId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoPost() {
        return textoPost;
    }

    public void setTextoPost(String textoPost) {
        this.textoPost = textoPost;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Timestamp getDtPublicacao() {
        return dtPublicacao;
    }

    public void setDtPublicacao(Timestamp dtPublicacao) {
        this.dtPublicacao = dtPublicacao;
    }

    public Integer getnLikes() {
        return nLikes;
    }

    public void setnLikes(Integer nLikes) {
        this.nLikes = nLikes;
    }

    public Integer getnComentarios() {
        return nComentarios;
    }

    public void setnComentarios(Integer nComentarios) {
        this.nComentarios = nComentarios;
    }

    public Integer getnCompartilhamentos() {
        return nCompartilhamentos;
    }

    public void setnCompartilhamentos(Integer nCompartilhamentos) {
        this.nCompartilhamentos = nCompartilhamentos;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
