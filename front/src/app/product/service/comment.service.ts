import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {api, environment} from '../../../environments/environment';
import {Comment} from '../../models/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) { }

  createComment(comment: Comment): Observable<Comment> {
    return this.httpClient.post<Comment>(`${environment.SERVER_ADDRESS}${api.COMMENT.CREATE_COMMENT}`, comment);
  }

}
