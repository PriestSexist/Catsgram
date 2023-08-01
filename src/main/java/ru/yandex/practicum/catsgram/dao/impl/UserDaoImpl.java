package ru.yandex.practicum.catsgram.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.catsgram.dao.UserDao;
import ru.yandex.practicum.catsgram.model.User;

import java.util.Optional;

@Slf4j
@Component
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    public Optional<User> findUserById(String id) {
        // выполняем запрос к базе данных. Результат пока игнорируем, мы будем работать с ним позже в этом уроке
        SqlRowSet userRows = jdbcTemplate.queryForRowSet("select * from cat_user where id = ?", id);
        if(userRows.next()) {
            // вы заполните данные пользователя в следующем уроке
            User user = new User(
                    userRows.getInt("id"),
                    userRows.getString("username"),
                    userRows.getString("nickname"));
            log.info("Найден пользователь: {} {}", user.getId(), user.getNickname());
            return Optional.of(user);
        } else {
            log.info("Пользователь с идентификатором {} не найден.", id);
            return Optional.empty();
        }
    }
}
