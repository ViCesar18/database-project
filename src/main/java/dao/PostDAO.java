package dao;

import model.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO extends DAO<Post>{
    void insertLikePost(Integer userId, Integer postId) throws SQLException;

    void deleteLikePost(Integer userId, Integer postId) throws SQLException;

    Integer numberOfLikes(Integer postId) throws SQLException;

    Boolean verificarLikePost(Integer userId, Integer postId) throws SQLException;

    void insertCompartilhamentoPost(Integer userId, Integer postId) throws SQLException;

    void deleteCompartilhamentoPost(Integer userId, Integer postId) throws SQLException;

    Integer numberOfCompartilhamentos(Integer postId) throws SQLException;

    Boolean verificarCompartilhamentoPost(Integer userId, Integer postId) throws SQLException;

    void insertBandPost(Integer postId, Integer bandId) throws SQLException;

    List<Integer> listSeguidoresBandaPost(Integer postId, Integer bandId) throws SQLException;

    List<Integer> listParticipantesBandaPost(Integer postId, Integer bandId) throws SQLException;
}
