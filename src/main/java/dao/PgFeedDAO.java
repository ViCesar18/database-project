package dao;

import model.Feed;
import model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgFeedDAO implements FeedDAO{
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.feed" +
            "(usuario_id) " +
            "VALUES(?);";

    private static final String READ_QUERY =
            "SELECT * " +
            "FROM rede_musical.feed " +
            "WHERE usuario_id = ?;";

    private static final String DELETE_QUERY =
            "DELETE FROM rede_musical.feed " +
            "WHERE usuario_id = ?";

    private static final String ALL_POSTS_OF_FEED =
            "SELECT * " +
            "FROM rede_musical.feed_possui_posts fpp " +
            "JOIN rede_musical.post p " +
            "ON fpp.post_id = p.id " +
            "WHERE fpp.feed_id = ?;";

    public PgFeedDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Feed feed) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY)) {
            statement.setInt(1, feed.getId());

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("pk_feed")) {
                throw new SQLException("Erro ao inserir: feed já cadastrado.");
            }
            else {
                throw new SQLException("Erro ao inserir feed.");
            }
        }
    }

    @Override
    public Feed read(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Feed feed) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: feed não encontrado.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: feed não encontrado.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar feed.");
            }
        }
    }

    @Override
    public List<Feed> all() throws SQLException {
        return null;
    }

    @Override
    public List<Post> allPostsFeed(Integer id) throws SQLException {
        List<Post> posts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(ALL_POSTS_OF_FEED);
             ResultSet result = statement.executeQuery()) {
            while(result.next()) {
                Post post = new Post();

                post.setId(result.getInt("id"));
                post.setTextoPost(result.getString("texto_post"));
                post.setImagem(result.getString("imagem"));
                post.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                post.setnLikes(result.getInt("n_likes"));
                post.setnComentarios(result.getInt("n_comentarios"));
                post.setnCompartilhamentos(result.getInt("n_compartilhamentos"));
                post.setUsuarioId(result.getInt("usuario_id"));

                posts.add(post);
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar posts.");
        }

        return posts;
    }
}
