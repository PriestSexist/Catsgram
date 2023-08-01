package ru.yandex.practicum.catsgram.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.FollowDao;
import ru.yandex.practicum.catsgram.dao.PostDao;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.Follow;
import ru.yandex.practicum.catsgram.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FollowDaoImpl implements FollowDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final PostDao postDao;

    @Autowired
    public FollowDaoImpl(JdbcTemplate jdbcTemplate, UserDao userDao, PostDao postDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
        this.postDao = postDao;
    }


    @Override
    public List<Post> getFollowFeed(String userId, int max) {
        String sql = "SELECT * FROM cat_follow WHERE follower_id = ?";

        List<Follow> follows = jdbcTemplate.query(sql, ((rs, rowNum) -> makeFollow(rs)), userId);

        return follows.stream()
                .map(Follow::getAuthor_id)
                .map(userDao::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(postDao::findPostsByUser)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .limit(max)
                .collect(Collectors.toList());
    }

    private Follow makeFollow(ResultSet rs) throws SQLException {
        // реализуйте маппинг результата запроса в объект класса Follow
        return new Follow(rs.getInt("id"), rs.getString("author_id"), rs.getString("follower_id"));
    }
}
