package dao;
import model.Banda;

import java.sql.SQLException;

public interface BandaDAO extends DAO<Banda> {
    void insertUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    void deleteUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Boolean readUsuarioSegueBanda(Integer usuarioId, Integer bandaId) throws SQLException;

    Integer readNumeroSeguidores(Integer id) throws SQLException;
}
