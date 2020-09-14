package dao;

import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgUsuarioDAO implements UsuarioDAO {
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.usuario" +
            "(username, email, senha, pnome, snome, dt_nascimento, imagem, cidade, estado, pais, " +
            "banda_favorita, musica_favorita, genero_favorito, instrumento_favorito) " +
            "VALUES(?, ?, md5(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT username, email, pnome, snome, dt_nascimento, imagem, cidade, " +
            "estado, pais, banda_favorita, musica_favorita, genero_favorito, instrumento_favorito " +
            "FROM rede_musical.usuario " +
            "WHERE id = ?;";

    private static final String UPDATE_PERSONAL_DATA_QUERY =
            "UPDATE rede_musical.usuario " +
            "SET username = ?, email = ?, pnome = ?, snome = ?, dt_nascimento = ?, cidade = ?, estado = ?, pais = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_MUSICAL_DATA_QUERY =
            "UPDATE rede_musical.usuario " +
            "SET banda_favorita = ?, musica_favorita = ?, genero_favorito = ?, instrumento_favorito = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_PASSWORD_QUERY =
            "UPDATE rede_musical.usuario " +
            "SET senha = md5(?) " +
            "WHERE id = ?;";

    private static final String UPDATE_IMAGE_QUERY =
            "UPDATE rede_musical.usuario " +
            "SET image = ? " +
            "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.usuario " +
            "WHERE id = ?;";

    private static final String ALL_QUERY =
            "SELECT id " +
            "FROM rede_musical.usuario " +
            "ORDER BY id;";

    private static final String AUTHENTICATE_QUERY =
            "SELECT id " +
            "FROM rede_musical.usuario " +
            "WHERE username = ? AND senha = md5(?);";

    public PgUsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Usuario usuario) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getpNome());
            statement.setString(5, usuario.getsNome());
            statement.setDate(6, usuario.getDtNascimento());
            statement.setString(7, usuario.getImagem());
            statement.setString(8, usuario.getCidade());
            statement.setString(9, usuario.getEstado());
            statement.setString(10, usuario.getPais());
            statement.setString(11, usuario.getBandaFavorita());
            statement.setString(12, usuario.getMusicaFavorita());
            statement.setString(13, usuario.getGeneroFavorito());
            statement.setString(14, usuario.getInstrumentoFavorito());

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("uq_usuario_username")) {
                throw new SQLException("Erro ao inserir usuário: usuário já existente.");
            }
            else if(e.getMessage().contains("uq_usuario_email")) {
                throw new SQLException("Erro ao inserir usuário: email já cadastrado.");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir usuário: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir usuário.");
            }
        }
    }

    @Override
    public Usuario read(Integer id) throws SQLException {
        Usuario usuario = new Usuario();

        try(PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    usuario.setId(id);
                    usuario.setUsername(result.getString("username"));
                    usuario.setEmail(result.getString("email"));
                    usuario.setpNome(result.getString("pnome"));
                    usuario.setsNome(result.getString("snome"));
                    usuario.setDtNascimento(result.getDate("dt_nascimento"));
                    usuario.setImagem(result.getString("imagem"));
                    usuario.setBandaFavorita(result.getString("banda_favorita"));
                    usuario.setMusicaFavorita(result.getString("musica_favorita"));
                    usuario.setGeneroFavorito(result.getString("genero-favorito"));
                    usuario.setInstrumentoFavorito(result.getString("instrumento-favorito"));
                }
                else {
                    throw new SQLException("Erro ao visualizar: usuário não encontrado.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao visualizar: usuário não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao visualizar.");
            }
        }

        return usuario;
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String query;

        if(usuario.getBandaFavorita() != null && !usuario.getBandaFavorita().isBlank()) {
            query = UPDATE_MUSICAL_DATA_QUERY;
        }
        else if(usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            query = UPDATE_PASSWORD_QUERY;
        }
        else if(usuario.getImagem() != null && !usuario.getImagem().isBlank()) {
            query = UPDATE_IMAGE_QUERY;
        }
        else {
            query = UPDATE_PERSONAL_DATA_QUERY;
        }

        try(PreparedStatement statement = connection.prepareStatement(query)) {
            if(usuario.getBandaFavorita() != null && !usuario.getBandaFavorita().isBlank()) {
                statement.setString(1, usuario.getBandaFavorita());
                statement.setString(2, usuario.getMusicaFavorita());
                statement.setString(3, usuario.getGeneroFavorito());
                statement.setString(4, usuario.getInstrumentoFavorito());
                statement.setInt(5, usuario.getId());
            }
            else if(usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
                statement.setString(1, usuario.getSenha());
                statement.setInt(2, usuario.getId());
            }
            else if(usuario.getImagem() != null && !usuario.getImagem().isBlank()) {
                statement.setString(1, usuario.getImagem());
                statement.setInt(2, usuario.getId());
            }
            else {
                statement.setString(1, usuario.getUsername());
                statement.setString(2, usuario.getEmail());
                statement.setString(3, usuario.getpNome());
                statement.setString(4, usuario.getsNome());
                statement.setDate(5, usuario.getDtNascimento());
                statement.setString(6, usuario.getCidade());
                statement.setString(7, usuario.getEstado());
                statement.setString(8, usuario.getPais());
                statement.setInt(9, usuario.getId());
            }

            if(statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: usuário não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao editar: usuário não encontrado.")) {
                throw e;
            }
            else if(e.getMessage().contains("uq_usuario_username")) {
                throw new SQLException("Erro ao editar: usuário já existe.");
            }
            else if(e.getMessage().contains("uq_usuario_email")) {
                throw new SQLException("Erro ao editar: email já cadastrado.");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao editar.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: usuário não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: usuário não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar.");
            }
        }
    }

    @Override
    public List<Usuario> all() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_QUERY);
             ResultSet result = statement.executeQuery()) {
            while(result.next()) {
                Usuario usuario = new Usuario();

                usuario.setId(result.getInt("id"));
                usuario.setUsername(result.getString("username"));

                usuarios.add(usuario);
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar usuários.");
        }

        return usuarios;
    }

    @Override
    public void authenticate(Usuario usuario) throws SQLException, SecurityException {
        try(PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_QUERY)) {
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getSenha());

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    usuario.setId(result.getInt("id"));
                }
                else {
                    throw new SecurityException("Usuário ou senha incorretos.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao autenticar usuário.");
        }
    }
}
