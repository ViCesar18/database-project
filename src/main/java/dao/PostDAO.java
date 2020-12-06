package dao;

import model.Post;

import java.sql.SQLException;

public interface PostDAO extends DAO<Post>{
    void insertLikePost(Integer postId) throws SQLException;

    void deleteLikePost(Integer postId) throws SQLException;

    Integer numberOfLikes(Integer postId) throws SQLException;

    void insertCompartilhamentoPost(Integer postId) throws SQLException;

    void deleteCompartilhamentoPost(Integer postId) throws SQLException;

    Integer numberOfCompartilhamentos(Integer postId) throws SQLException;
}
