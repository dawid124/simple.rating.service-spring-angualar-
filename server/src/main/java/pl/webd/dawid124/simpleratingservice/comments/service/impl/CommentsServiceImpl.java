package pl.webd.dawid124.simpleratingservice.comments.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.comments.mapper.CommentsMapper;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;
import pl.webd.dawid124.simpleratingservice.comments.service.CommentsService;

@Service
@Transactional
public class CommentsServiceImpl implements CommentsService {

    private CommentsMapper commentsMapper;

    public CommentsServiceImpl(CommentsMapper commentsMapper) {
        this.commentsMapper = commentsMapper;
    }

    @Override
    public int createComment(Comment comment) {
        return commentsMapper.insertComment(comment);
    }
}
