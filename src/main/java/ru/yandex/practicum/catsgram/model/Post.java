package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Post {
    private Integer id;
    private final User author; // автор
    private String description; // описание
    private String photoUrl; // url-адрес фотографии
    private LocalDate creationDate; // дата создания

    public Post(User author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
        this.creationDate = LocalDate.now();
    }

}
