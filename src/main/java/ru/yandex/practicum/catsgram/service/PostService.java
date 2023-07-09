package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public PostService (UserService userService){
        this.userService = userService;
    }
    public List<Post> findAll(int from, int size, String sort) {
        switch (sort) {

            case "asc":
                return posts.stream()
                        .sorted(Comparator.comparing(Post::getCreationDate))
                        .skip((long) (from - 1) * size)
                        .limit(size)
                        .collect(Collectors.toList());

            case "desc":
                return posts.stream()
                        .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                        .skip((long) (from - 1) * size)
                        .limit(size)
                        .collect(Collectors.toList());
            default:
                throw new RuntimeException("Ошибка чего-то там");
        }
    }

    public Post getPostById(int id){
        return posts.stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElseThrow(() -> new PostNotFoundException(String.format("Пост № %d не найден", id)));
    }

    public Post createPost(Post post) {
        post.setId(count.incrementAndGet());
        if (userService.getUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        posts.add(post);
        return post;
    }
}