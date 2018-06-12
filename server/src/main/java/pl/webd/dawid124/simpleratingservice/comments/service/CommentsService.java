package pl.webd.dawid124.simpleratingservice.comments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;

@Service
@Transactional
public interface CommentsService {

    int createComment(Comment comment);
}
