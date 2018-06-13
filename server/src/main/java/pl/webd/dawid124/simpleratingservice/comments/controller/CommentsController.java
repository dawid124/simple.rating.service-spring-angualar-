package pl.webd.dawid124.simpleratingservice.comments.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.webd.dawid124.simpleratingservice.comments.model.Comment;
import pl.webd.dawid124.simpleratingservice.comments.service.CommentsService;

@RestController
public class CommentsController {

    private CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> creteNewRating(@RequestBody Comment comment) throws AuthenticationException {

        if (!comment.valid()) {
            return new ResponseEntity<>("NOT_VALID", HttpStatus.BAD_REQUEST);
        }

       commentsService.createComment(comment);

        if (comment.getId() > 0) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
