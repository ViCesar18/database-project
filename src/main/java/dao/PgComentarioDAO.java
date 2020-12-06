package dao;

import model.Comentario;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PgComentarioDAO implements ComentarioDAO {
    private final Connection connection;

    private static final String CREATE_QUERY =
            "INSERT INTO rede_musical.comentario" +
            "(dt_publicacao, texto_comentario) " +
            "VALUES(CURRENT_TIMESTAMP, ?);";

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

    public PgComentarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Comentario comentario) throws SQLException {
        try(PreparedStatement statement = this.connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, comentario.getTextoComentario());

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
}
