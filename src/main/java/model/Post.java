package model;

import java.sql.Timestamp;
import java.util.List;

public class Post {
    private Integer id;
    private String textoPost;
    private String imagem;
    private Timestamp dtPublicacao;
    private Integer usuarioId;
    private Usuario usuario;
    private Boolean curtiu;
    private Boolean compartilhou;

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    private List<Comentario> comentarios;

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

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getCurtiu() {
        return curtiu;
    }

    public void setCurtiu(Boolean curtiu) {
        this.curtiu = curtiu;
    }

    public Boolean getCompartilhou() {
        return compartilhou;
    }

    public void setCompartilhou(Boolean compartilhou) {
        this.compartilhou = compartilhou;
    }
}
