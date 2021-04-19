package model;

public class Estatisticas {
    private Integer id;

    private Integer numeroLikes;

    private Integer numeroComentarios;

    private Integer numeroCompartilhamentos;

    private Integer numeroInteracoes;

    private String instrumento;

    private Integer frequencia;

    private String generoFavorito;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(Integer numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public Integer getNumeroComentarios() {
        return numeroComentarios;
    }

    public void setNumeroComentarios(Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    public Integer getNumeroCompartilhamentos() {
        return numeroCompartilhamentos;
    }

    public void setNumeroCompartilhamentos(Integer numeroCompartilhamentos) {
        this.numeroCompartilhamentos = numeroCompartilhamentos;
    }

    public Integer getNumeroInteracoes() {
        return numeroInteracoes;
    }

    public void setNumeroInteracoes(Integer numeroInteracoes) {
        this.numeroInteracoes = numeroInteracoes;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public String getGeneroFavorito() {
        return generoFavorito;
    }

    public void setGeneroFavorito(String generoFavorito) {
        this.generoFavorito = generoFavorito;
    }
}
