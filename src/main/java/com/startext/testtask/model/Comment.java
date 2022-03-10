package com.startext.testtask.model;

import com.startext.testtask.entity.CommentEntity;

public class Comment {
    private String userId;
    private String content;

    public static Comment toModel(CommentEntity comment) {
        Comment model = new Comment();
        model.setContent(comment.getContent());
        return model;
    }

    public Comment() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
