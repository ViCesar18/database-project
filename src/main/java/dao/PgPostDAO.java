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
            "(texto_post, imagem, dt_publicacao, usuario_id) " +
            "VALUES(?, ?, CURRENT_TIMESTAMP, ?);";

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

    private static final String INSERT_LIKE_POST =
            "INSERT INTO rede_musical.usuario_da_like_em_post " +
            "(usuario_id, post_id) " +
            "VALUES(?, ?);";

    private static final String DELETE_LIKE_POST =
            "DELETE FROM rede_musical.usuario_da_like_em_post " +
            "WHERE usuario_id = ? AND post_id = ?;";

    private static final String NUMBER_OF_LIKES =
            "SELECT COUNT(*) AS likes " +
            "FROM rede_musical.usuario_da_like_em_post udlep " +
            "WHERE post_id = ?;";

    private static final String VERIRIFCAR_LIKE_POST =
            "SELECT COUNT(*) > 0 AS curtiu " +
            "FROM rede_musical.usuario_da_like_em_post udlep " +
            "WHERE usuario_id = ? AND post_id = ?;";

    private static final String INSERT_COMPARTILHAMENTO_POST =
            "INSERT INTO rede_musical.usuario_compartilha_post " +
            "(usuario_id, post_id) " +
            "VALUES(?, ?);";

    private static final String DELETE_COMPARTILHAMENTO_POST =
            "DELETE FROM rede_musical.usuario_compartilha_post " +
            "WHERE post_id = ?;";

    private static final String NUMBER_OF_COMPARTILHAMENTOS =
            "SELECT COUNT(*) AS compartilhamentos " +
            "FROM rede_musical.usuario_compartilha_post " +
            "WHERE post_id = ?;";

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

    @Override
    public void insertLikePost(Integer userId, Integer postId) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT_LIKE_POST)) {
            statement.setInt(1, userId);
            statement.setInt(2, postId);

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("fk_usuario_like_post")) {
                throw new SQLException("Erro ao inserir: post já está curtido.");
            }
            else {
                throw new SQLException("Erro ao inserir like no post.");
            }
        }
    }

    @Override
    public void deleteLikePost(Integer userId, Integer postId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_LIKE_POST)) {
            statement.setInt(1, userId);
            statement.setInt(2, postId);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: like não encontrado no post.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: like não encontrado no post.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar like no post.");
            }
        }
    }

    @Override
    public Integer numberOfLikes(Integer postId) throws SQLException {
        Integer likes;

        try(PreparedStatement statement = connection.prepareStatement(NUMBER_OF_LIKES)) {
            statement.setInt(1, postId);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    likes = result.getInt("likes");
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

        return likes;
    }

    @Override
    public Boolean verificarLikePost(Integer userId, Integer postId) throws SQLException {
        Boolean curtiu = false;

        try(PreparedStatement statement = connection.prepareStatement(VERIRIFCAR_LIKE_POST)) {
            statement.setInt(1, userId);
            statement.setInt(2, postId);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    String segueStr = result.getString("curtiu");

                    curtiu = segueStr.equals("t") ? true : false;
                }
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            throw new SQLException("Erro verificar se usuario curte post.");
        }

        return curtiu;
    }

    @Override
    public void insertCompartilhamentoPost(Integer postId) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT_COMPARTILHAMENTO_POST)) {
            statement.setInt(1, postId);

            statement.executeUpdate();
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().contains("pk_usuario_compartilha_post")) {
                throw new SQLException("Erro ao inserir: post já está compartilhado.");
            }
            else {
                throw new SQLException("Erro ao inserir compartilhamento no post.");
            }
        }
    }

    @Override
    public void deleteCompartilhamentoPost(Integer postId) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_COMPARTILHAMENTO_POST)) {
            statement.setInt(1, postId);

            if (statement.executeUpdate() < 1) {
                throw new SQLException("Erro ao deletar: compartilhamento não encontrado no post.");
            }
        } catch(SQLException e) {
            Logger.getLogger(PgUsuarioDAO.class.getName()).log(Level.SEVERE, "DAO", e);

            if(e.getMessage().equals("Erro ao deletar: compartilhamento não encontrado no post.")) {
                throw e;
            }
            else {
                throw new SQLException("Erro ao deletar compartilhamento no post.");
            }
        }
    }

    @Override
    public Integer numberOfCompartilhamentos(Integer postId) throws SQLException {
        Integer compartilhamentos;

        try(PreparedStatement statement = connection.prepareStatement(NUMBER_OF_COMPARTILHAMENTOS)) {
            statement.setInt(1, postId);

            try(ResultSet result = statement.executeQuery()) {
                if(result.next()) {
                    compartilhamentos = result.getInt("compartilhamentos");
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

        return compartilhamentos;
    }
}
