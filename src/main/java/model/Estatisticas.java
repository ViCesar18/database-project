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

    private int geracao_boomer_count;

    private int geracao_x_count;

    private int geracao_millennials_count;

    private int geracao_z_count;

    public int getGeracao_boomer_count() {
        return geracao_boomer_count;
    }

    public void setGeracao_boomer_count(int geracao_boomer_count) {
        this.geracao_boomer_count = geracao_boomer_count;
    }

    public int getGeracao_x_count() {
        return geracao_x_count;
    }

    public void setGeracao_x_count(int geracao_x_count) {
        this.geracao_x_count = geracao_x_count;
    }

    public int getGeracao_millennials_count() {
        return geracao_millennials_count;
    }

    public void setGeracao_millennials_count(int geracao_millennials_count) {
        this.geracao_millennials_count = geracao_millennials_count;
    }

    public int getGeracao_z_count() {
        return geracao_z_count;
    }

    public void setGeracao_z_count(int geracao_z_count) {
        this.geracao_z_count = geracao_z_count;
    }

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
