import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from '../../service/comment.service';
import {Product} from '../../../models/product';
import {Comment} from '../../../models/comment';
import {AuthService} from '../../../auth/auth.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

  @Input('product') product: Product;
  newComment: Comment;

  constructor(private commentService: CommentService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.clearNewCommentData();
  }

  addComment() {
    this.newComment.date = new Date();
    this.newComment.userId = this.authService.userData.id;
    this.newComment.productId = this.product.id;

    this.commentService.createComment(this.newComment)
      .subscribe((comment: Comment) => {
        this.product.comments.push(comment);
        this.clearNewCommentData();
      });
  }

  clearNewCommentData() {
    this.newComment = new Comment();
  }

  isLogged() {
    if (this.authService.userData) {
      return true;
    }
    return false;
  }
}
