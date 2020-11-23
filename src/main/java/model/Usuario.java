package model;

import java.sql.Date;

public class Usuario {
    private Integer id;
    private String username;
    private String email;
    private String senha;
    private String pNome;
    private String sNome;
    private Date dtNascimento;
    private String imagem;
    private String bandaFavorita;
    private String musicaFavorita;
    private String generoFavorito;
    private String instrumentoFavorito;
    private String cidade;
    private String estado;
    private String pais;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getpNome() {
        return pNome;
    }

    public void setpNome(String pNome) {
        this.pNome = pNome;
    }

    public String getsNome() {
        return sNome;
    }

    public void setsNome(String sNome) {
        this.sNome = sNome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) { this.dtNascimento = dtNascimento; }

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }

    public String getCidade() { return cidade; }

    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { this.estado = estado; }

    public String getPais() { return pais; }

    public void setPais(String pais) { this.pais = pais; }

    public String getBandaFavorita() {
        return bandaFavorita;
    }

    public void setBandaFavorita(String bandaFavorita) { this.bandaFavorita = bandaFavorita; }

    public String getMusicaFavorita() {
        return musicaFavorita;
    }

    public void setMusicaFavorita(String musicaFavorita) {
        this.musicaFavorita = musicaFavorita;
    }

    public String getGeneroFavorito() {
        return generoFavorito;
    }

    public void setGeneroFavorito(String generoFavorito) {
        this.generoFavorito = generoFavorito;
    }

    public String getInstrumentoFavorito() {
        return instrumentoFavorito;
    }

    public void setInstrumentoFavorito(String instrumentoFavorito) {
        this.instrumentoFavorito = instrumentoFavorito;
    }
}
