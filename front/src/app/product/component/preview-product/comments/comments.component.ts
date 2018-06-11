import { Component, OnInit } from '@angular/core';
import {CommentService} from '../../../service/comment.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.scss']
})
export class CommentsComponent implements OnInit {

  constructor(private commentService: CommentService) { }

  ngOnInit() {
  }

  addComment() {

  }
}
