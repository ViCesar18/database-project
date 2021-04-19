package dao;

import model.Comentario;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface ComentarioDAO extends DAO<Comentario> {

    Integer numberOfComents(Integer postId) throws SQLException;

    List<Comentario> allComentsPost(Integer postId, Timestamp data) throws SQLException;

    List<Comentario> fiveFirstCommentsPost(Integer postId, Timestamp data) throws SQLException;
}
