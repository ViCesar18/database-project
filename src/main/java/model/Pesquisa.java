package model;

public class Pesquisa {
    private String pesquisa;
    private Boolean filtroUsuario;
    private Boolean filtroBanda;
    private Boolean filtroEvento;

    private Integer idUsuario;
    private String usernameUsuario;
    private String nomeUsuario;
    private Boolean usuarioLogadoSegueUsuario;

    private Integer idBanda;
    private String siglaBanda;
    private String nomeBanda;
    private Boolean usuarioLogadoSegueBanda;

    private Boolean usuarioLogadoCompareceEvento;
    private Integer idCriadorBanda;

    private Integer idEvento;
    private String nomeEvento;

    public Boolean getUsuarioLogadoParticipaBanda() {
        return usuarioLogadoParticipaBanda;
    }

    public void setUsuarioLogadoParticipaBanda(Boolean usuarioLogadoParticipaBanda) {
        this.usuarioLogadoParticipaBanda = usuarioLogadoParticipaBanda;
    }

    private Boolean usuarioLogadoParticipaBanda;

    public Boolean getUsuarioLogadoCompareceEvento() {
        return usuarioLogadoCompareceEvento;
    }

    public void setUsuarioLogadoCompareceEvento(Boolean usuarioLogadoCompareceEvento) {
        this.usuarioLogadoCompareceEvento = usuarioLogadoCompareceEvento;
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public Boolean getFiltroUsuario() {
        return filtroUsuario;
    }

    public void setFiltroUsuario(Boolean filtroUsuario) {
        this.filtroUsuario = filtroUsuario;
    }

    public Boolean getFiltroBanda() {
        return filtroBanda;
    }

    public void setFiltroBanda(Boolean filtroBanda) {
        this.filtroBanda = filtroBanda;
    }

    public Boolean getFiltroEvento() {
        return filtroEvento;
    }

    public void setFiltroEvento(Boolean filtroEvento) {
        this.filtroEvento = filtroEvento;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public void setUsernameUsuario(String usernameUsuario) {
        this.usernameUsuario = usernameUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Boolean getUsuarioLogadoSegueUsuario() { return usuarioLogadoSegueUsuario; }

    public void setUsuarioLogadoSegueUsuario(Boolean usuarioLogadoSegueUsuario) { this.usuarioLogadoSegueUsuario = usuarioLogadoSegueUsuario; }

    public String getSiglaBanda() {
        return siglaBanda;
    }

    public void setSiglaBanda(String siglaBanda) {
        this.siglaBanda = siglaBanda;
    }

    public String getNomeBanda() {
        return nomeBanda;
    }

    public void setNomeBanda(String nomeBanda) {
        this.nomeBanda = nomeBanda;
    }

    public Boolean getUsuarioLogadoSegueBanda() { return usuarioLogadoSegueBanda; }

    public void setUsuarioLogadoSegueBanda(Boolean usuarioLogadoSegueBanda) { this.usuarioLogadoSegueBanda = usuarioLogadoSegueBanda; }

    public Integer getIdCriadorBanda() { return idCriadorBanda; }

    public void setIdCriadorBanda(Integer idCriadorBanda) { this.idCriadorBanda = idCriadorBanda; }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(Integer idBanda) {
        this.idBanda = idBanda;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }
}
