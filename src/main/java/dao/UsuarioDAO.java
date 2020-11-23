package dao;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO extends DAO<Usuario> {

    void authenticate(Usuario usuario) throws SQLException, SecurityException;

    void insertInstrumento(Integer id, String instrumento) throws SQLException;

    public List<String> readInstrumentos(Integer id) throws SQLException;
}
