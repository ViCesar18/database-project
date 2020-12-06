package dao;

import model.Evento;

import java.sql.SQLException;

public interface EventoDAO extends DAO<Evento> {
    void insertUsuarioCompareceEvento(Integer idUsuario, Integer idEvento) throws SQLException;

    void deleteUsuarioCompareceEvento(Integer idUsuario, Integer idEvento) throws SQLException;

    Boolean readUsuarioCompareceEvento(Integer idUsuario, Integer idEvento) throws SQLException;

    public Integer readNumeroParticipantes(Integer id) throws SQLException;
}


