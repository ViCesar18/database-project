package model;

import java.sql.Timestamp;

public class Evento {
    private int id;
    private String nome;
    private String descricao;
    private Timestamp data;
    private int nParticipantes;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getnParticipantes() {
        return nParticipantes;
    }

    public void setnParticipantes(int nParticipantes) {
        this.nParticipantes = nParticipantes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
