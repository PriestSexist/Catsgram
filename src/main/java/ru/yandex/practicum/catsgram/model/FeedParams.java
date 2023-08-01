package ru.yandex.practicum.catsgram.model;

import java.util.List;

public class FeedParams {
    private String sort;
    private Integer size;
    private List<String> friendsIds;

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setFriendsIds(List<String> friendsEmails) {
        this.friendsIds = friendsEmails;
    }

    public List<String> getFriendsIds() {
        return friendsIds;
    }
}
