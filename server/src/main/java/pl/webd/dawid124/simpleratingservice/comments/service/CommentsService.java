package pl.webd.dawid124.simpleratingservice.comments.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;

import java.util.List;

@Service
@Transactional
public interface CommentsService {

    Comment createComment(Comment comment);

    List<String> getCommentingUsers(long productId);
}
