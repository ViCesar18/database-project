package dao;

import model.Usuario;

import java.sql.SQLException;

public interface UsuarioDAO extends DAO<Usuario> {

    void authenticate(Usuario usuario) throws SQLException, SecurityException;
}
