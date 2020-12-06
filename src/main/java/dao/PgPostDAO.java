package dao;

import model.Post;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgPostDAO implements PostDAO{
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.post" +
            "(texto_post, imagem, dt_publicacao, n_likes, n_comentarios, n_compartilhamentos, usuario_id) " +
            "VALUES(?, ?, CURRENT_TIMESTAMP, 0, 0, 0, ?);";

    private static final String READ_QUERY =
            "SELECT * " +
            "FROM rede_musical.post " +
            "WHERE id = ?;";

    private static final String UPDATE_QUERY =
            "UPDATE rede_musical.post " +
            "SET texto_post = ? " +
            "WHERE id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.post " +
            "WHERE id = ?";

    public PgPostDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Post post) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTextoPost());
            statement.setString(2, post.getImagem());
            statement.setInt(3, post.getUsuarioId());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    post.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Erro ao inserir post: não obteve o ID.");
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("pk_feed_possui_posts")) {
                throw new SQLException("Erro ao inserir post: post já existente.");
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao inserir post: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao inserir post.");
            }
        }
    }

    @Override
    public Post read(Integer id) throws SQLException {
        Post post = new Post();

        try(PreparedStatement statement = connection.prepareStatement(READ_QUERY)) {
            statement.setInt(1, id);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    post.setId(result.getInt("id"));
                    post.setTextoPost(result.getString("texto_post"));
                    post.setImagem(result.getString("imagem"));
                    post.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                    post.setnLikes(result.getInt("n_likes"));
                    post.setnComentarios(result.getInt("n_comentarios"));
                    post.setnCompartilhamentos(result.getInt("n_compartilhamentos"));
                    post.setUsuarioId(result.getInt("usuario_id"));
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

        return post;
    }

    @Override
    public void update(Post post) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, post.getTextoPost());
            statement.setInt(2, post.getId());

            if(statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao editar: post não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao editar: post não encontrado.")) {
                throw e;
            }
            else if(e.getMessage().contains("not-null")) {
                throw new SQLException("Erro ao editar: pelo menos um campo está em branco.");
            }
            else {
                throw new SQLException("Erro ao editar post.");
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: post não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: post não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar post.");
            }
        }
    }

    @Override
    public List<Post> all() throws SQLException {
        return null;
    }
}
