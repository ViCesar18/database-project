package dao;

import model.Comentario;
import model.Post;
import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgComentarioDAO implements ComentarioDAO {
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.comentario" +
            "(dt_publicacao, texto_comentario, usuario_id, post_id) " +
            "VALUES(CURRENT_TIMESTAMP, ?, ?, ?);";

    private static final String READ_QUERY =
            "SELECT * " +
            "FROM rede_musical.comentario " +
            "WHERE id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE rede_musical.comentario " +
            "SET texto_comentario = ? " +
            "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.comentario " +
            "WHERE id = ?";

    private static final String ALL_COMMENTS_OF_POST =
            "SELECT c.*, u.pnome, u.snome, u.imagem " +
            "FROM rede_musical.comentario c " +
            "JOIN rede_musical.usuario u ON c.usuario_id = u.id " +
            "WHERE post_id = ?;";

    private static final String NUMBER_OF_COMMENTS =
            "SELECT COUNT(*) AS n_comentarios " +
            "FROM rede_musical.comentario c " +
            "WHERE post_id = ?;";

    public PgComentarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Comentario comentario) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comentario.getTextoComentario());
            statement.setInt(2, comentario.getUsuarioId());
            statement.setInt(3, comentario.getPostId());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comentario.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Erro ao inserir comentario: não obteve o ID.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("pk_comentario")) {
                throw new SQLException("Erro ao inserir comentario: comentario já existente.");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir comentario: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir comentario.");
            }
        }
    }

    @Override
    public Comentario read(Integer id) throws SQLException {
        Comentario comentario = new Comentario();

        try(PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    comentario.setId(result.getInt("id"));
                    comentario.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                    comentario.setTextoComentario(result.getString("texto_comentario"));
                    comentario.setUsuarioId(result.getInt("usuario_id"));
                    comentario.setPostId(result.getInt("post_id"));
                }
                else {
                    throw new SQLException("Erro ao visualizar: comentário não encontrado.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao visualizar: comentário não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao visualizar comentário.");
            }
        }

        return comentario;
    }

    @Override
    public void update(Comentario comentario) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, comentario.getTextoComentario());

            if(statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: comentário não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao editar: comentário não encontrado.")) {
                throw e;
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao editar comentário.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: comentário não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: comentário não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar comentário.");
            }
        }
    }

    @Override
    public List<Comentario> all() throws SQLException {
        return null;
    }

    @Override
    public List<Comentario> allComentsPost(Integer postId) throws SQLException {
        List<Comentario> comentarios = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_COMMENTS_OF_POST);
             ResultSet result = statement.executeQuery()) {
            while(result.next()) {
                Comentario comentario = new Comentario();
                Usuario usuario = new Usuario();

                comentario.setId(result.getInt("id"));
                comentario.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                comentario.setTextoComentario(result.getString("texto_comentario"));
                comentario.setPostId(result.getInt("post_id"));
                comentario.setUsuarioId(result.getInt("usuario_id"));
                usuario.setId(result.getInt("usuario_id"));
                usuario.setpNome(result.getString("pnome"));
                usuario.setsNome(result.getString("snome"));
                usuario.setImagem(result.getString("imagem"));
                comentario.setUsuario(usuario);

                comentarios.add(comentario);
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar comentários.");
        }

        return comentarios;
    }

    @Override
    public Integer numberOfComents(Integer postId) throws SQLException {
        Integer nComentarios;

        try(PreparedStatement statement = connection.prepareStatement(NUMBER_OF_COMMENTS)) {
            statement.setInt(1, postId);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    nComentarios = result.getInt("n_comentarios");
                }
                else {
                    throw new SQLException("Erro ao visualizar: post não encontrado.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao visualizar: post não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao visualizar post.");
            }
        }

        return nComentarios;
    }
}
