package ru.yandex.practicum.catsgram.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Follow {
    Integer id;
    String author_id;
    String follower_id;
}
