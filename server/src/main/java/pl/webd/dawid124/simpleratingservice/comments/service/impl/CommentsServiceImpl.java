package pl.webd.dawid124.simpleratingservice.comments.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.comments.mapper.CommentsMapper;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;
import pl.webd.dawid124.simpleratingservice.comments.service.CommentsService;
import pl.webd.dawid124.simpleratingservice.interceptors.SendNotifications;

import java.util.List;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private CommentsMapper commentsMapper;

    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    @SendNotifications
    public Comment createComment(Comment comment) {
        commentsMapper.insertComment(comment);

        return comment;
    }

    @Override
    public List<String> getCommentingUsers(long productId) {
        return commentsMapper.getCommentingUsers(productId);
    }
}
