package dao;

import model.Comentario;

import java.sql.SQLException;
import java.util.List;

public interface ComentarioDAO extends DAO<Comentario> {
    List<Comentario> allComentsPost(Integer postId) throws SQLException;

    Integer numberOfComents(Integer postId) throws SQLException;
}
