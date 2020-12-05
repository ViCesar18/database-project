package dao;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO extends DAO<Usuario> {

    void authenticate(Usuario usuario) throws SQLException, SecurityException;

    void insertInstrumento(Integer id, String instrumento) throws SQLException;

    List<String> readInstrumentos(Integer id) throws SQLException;

    void insertUsuarioSegueUsuario(Integer id, Integer idSeguido) throws SQLException;

    void deleteUsuarioSegueUsuario(Integer id, Integer idSeguido) throws SQLException;

    Boolean readUsuarioSegueUsuario(Integer id, Integer idSeguido) throws SQLException;

    Integer readNumeroSeguidores(Integer id) throws SQLException;

    Integer readNumeroSeguindo(Integer id) throws SQLException;
}
