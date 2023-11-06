create table cat_follow
(
    id          int generated by default as identity primary key,
    author_id   varchar(64) REFERENCES cat_user(id),
    follower_id varchar(64) REFERENCES cat_user(id)
);

INSERT INTO cat_follow (author_id, follower_id) VALUES('grumpy', 'tom');

INSERT INTO public.cat_post (author_id, description, photo_url, creation_date) VALUES ('grumpy', 'Мой новый пост!!111', 'file:///storage/catsgram/grumpy/2/image.png', '2022-03-08 15:24:03.000000');