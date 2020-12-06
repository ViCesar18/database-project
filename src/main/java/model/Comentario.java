package model;

import java.sql.Timestamp;

public class Comentario {
    private Integer id;
    private Timestamp dtPublicacao;
    String textoComentario;

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
}
