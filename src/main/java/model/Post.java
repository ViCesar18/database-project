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
    private Banda banda;
    private Boolean curtiu;
    private Boolean compartilhou;
    private Integer nCurtidas;
    private Integer nCompartilhamentos;
    private Integer nComentarios;
    private List<Comentario> comentarios;
    private List<Comentario> fiveFirstComentarios;

    public Integer getnCompartilhamentos() {
        return nCompartilhamentos;
    }

    public void setnCompartilhamentos(Integer nCompartilhamentos) {
        this.nCompartilhamentos = nCompartilhamentos;
    }

    public Integer getnComentarios() {
        return nComentarios;
    }

    public void setnComentarios(Integer nComentarios) {
        this.nComentarios = nComentarios;
    }

    public Integer getnCurtidas() {
        return nCurtidas;
    }

    public void setnCurtidas(Integer nCurtidas) {
        this.nCurtidas = nCurtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Comentario> getFiveFirstComentarios(){return fiveFirstComentarios;}

    public void setFiveFirstComentarios (List<Comentario> comentarios){
        this.fiveFirstComentarios = comentarios;
    }

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

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
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
