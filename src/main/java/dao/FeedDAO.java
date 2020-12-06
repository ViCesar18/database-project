package dao;

import model.Feed;
import model.Post;

import java.sql.SQLException;
import java.util.List;

public interface FeedDAO extends DAO<Feed>{
    List<Post> allPostsFeed(Integer id) throws SQLException;
}
