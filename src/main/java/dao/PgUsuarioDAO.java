package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PgUsuarioDAO implements UsuarioDAO {
    private final Connection connection;

    public PgUsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) throws SQLException {

    }

    @Override
    public Usuario read() throws SQLException {
        return null;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public List<Usuario> all() throws SQLException {
        return null;
    }

    @Override
    public void authenticate(Usuario usuario) throws SQLException, SecurityException {

    }

    @Override
    public Usuario getByLogin(String login) throws SQLException {
        return null;
    }
}
