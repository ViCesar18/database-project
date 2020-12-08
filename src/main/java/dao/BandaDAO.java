package dao;
import model.Banda;
import model.Post;

import java.sql.SQLException;
import java.util.List;

public interface BandaDAO extends DAO<Banda> {
    void insertUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    void deleteUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Boolean readUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Integer readNumeroSeguidores(Integer id) throws SQLException;

    void insertUsuarioParticipaBanda(Integer idUsuario, Integer idBanda, String instrumento) throws SQLException;

    void deleteUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException;

    Boolean readUsuarioParticipaBanda(Integer idUsuario, Integer idBanda) throws SQLException;

    Integer readNumeroParticipantes(Integer id) throws SQLException;

    Integer getBandaId(String bandaNome, String bandaSigla) throws SQLException;

    List<Post> allPostsBanda(Integer bandaId) throws SQLException;
}
