package model;

public class Banda {
    private int id;
    private String sigla;
    private String nome;
    private String genero;
    private String imagem;
    private int username_id;

    public int getUsername_id() {
        return username_id;
    }

    public void setUsername_id(int username_id) {
        this.username_id = username_id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) { this.imagem = imagem; }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
