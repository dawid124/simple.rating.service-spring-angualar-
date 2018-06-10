package pl.webd.dawid124.simpleratingservice.comments.service.impl;

import pl.webd.dawid124.simpleratingservice.comments.mapper.CommentsMapper;

public class CommentsServiceImpl {

    private CommentsMapper commentsMapper;

    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }
}
