package dao;
import model.Banda;

import java.sql.SQLException;

public interface BandaDAO extends DAO<Banda> {
    void insertUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    void deleteUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Boolean readUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Integer readNumeroSeguidores(Integer id) throws SQLException;

    public void insertUsuarioParticipaBanda(Integer idUsuario, Integer idBanda, String instrumento) throws SQLException;

    public void deleteUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException;

    public Boolean readUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException;

    public Integer readNumeroParticipantes(Integer id) throws SQLException;

    public Integer getBandaId(String bandaNome, String bandaSigla) throws SQLException;
}
