package dao;

import model.Feed;
import model.Post;
import model.Usuario;

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
            "SELECT p.*, u.pnome, u.snome, u.imagem AS imagem_usuario " +
            "FROM rede_musical.feed_possui_posts fpp " +
            "JOIN rede_musical.post p ON fpp.post_id = p.id " +
            "JOIN rede_musical.usuario u ON p.usuario_id = u.id " +
            "WHERE fpp.feed_id = ? " +
            "ORDER BY dt_publicacao DESC;";

    private static final String INSERT_POST_IN_FEED =
            "INSERT INTO rede_musical.feed_possui_posts " +
            "(feed_id, post_id, id_usuario_compartilhou) " +
            "VALUES(?, ?, ?);";

    private static final String DELETE_POST_IN_FEED =
            "DELETE FROM rede_musical.feed_possui_posts " +
            "WHERE post_id = ?;";

    private static final String DELETE_POST_COMPARTILHAMENTO =
            "DELETE FROM rede_musical.feed_possui_posts " +
            "WHERE post_id = ? AND id_usuario_compartilhou = ?;";

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

        try (PreparedStatement statement = connection.prepareStatement(ALL_POSTS_OF_FEED)) {
            statement.setInt(1, id);
                try(ResultSet result = statement.executeQuery()){
                    while(result.next()) {
                        Post post = new Post();
                        Usuario usuario = new Usuario();

                        post.setId(result.getInt("id"));
                        post.setTextoPost(result.getString("texto_post"));
                        post.setImagem(result.getString("imagem"));
                        post.setDtPublicacao(result.getTimestamp("dt_publicacao"));
                        post.setUsuarioId(result.getInt("usuario_id"));
                        usuario.setId(result.getInt("usuario_id"));
                        usuario.setpNome(result.getString("pnome"));
                        usuario.setsNome(result.getString("snome"));
                        usuario.setImagem(result.getString("imagem_usuario"));
                        post.setUsuario(usuario);

                        posts.add(post);
                    }
                }catch (SQLException e) {
                    throw new SQLException("Erro verificar posts.");
                }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro ao listar posts.");
        }

        return posts;
    }

    @Override
    public void insertPostInFeed(Integer feedId, Integer postId, Integer idUsuarioCompartilhou) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT_POST_IN_FEED)) {
            statement.setInt(1, feedId);
            statement.setInt(2, postId);
            statement.setInt(3, idUsuarioCompartilhou);

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("pk_feed_possui_posts")) {
                throw new SQLException("Erro ao inserir: post já está no feed.");
            }
            else {
                throw new SQLException("Erro ao inserir post no feed.");
            }
        }
    }

    @Override
    public void deletePostOfFeed(Integer postId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_POST_IN_FEED)) {
            statement.setInt(1, postId);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: post não encontrado no feed.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: post não encontrado no feed.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar post no feed.");
            }
        }
    }

    @Override
    public void deletePostCompartilhamento(Integer postId, Integer idUsuarioCompartilhou) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_POST_COMPARTILHAMENTO)) {
            statement.setInt(1, postId);
            statement.setInt(2, idUsuarioCompartilhou);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: post(compartilhamento) não encontrado no feed.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: post(compartilhamento) não encontrado no feed.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar post(compartilhamento) no feed.");
            }
        }
    }
}
