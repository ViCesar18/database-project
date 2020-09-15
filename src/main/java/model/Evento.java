package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Evento {
    private int id;
    private String nome;
    private String descricao;
    private Timestamp data_inicio;
    private Timestamp data_termino;
    private int nParticipantes;
    private String categoria;
    private int username_id;

    public int getUsername_id() {
        return username_id;
    }

    public void setUsername_id(int username_id) {
        this.username_id = username_id;
    }

    public Timestamp getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Timestamp data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Timestamp getData_termino() {
        return data_termino;
    }

    public void setData_termino(Timestamp data_termino) {
        this.data_termino = data_termino;
    }

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
