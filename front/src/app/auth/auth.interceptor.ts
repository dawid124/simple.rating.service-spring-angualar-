import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';

import {AuthService} from './auth.service';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  constructor() {

  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (AuthService.token) {

      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${AuthService.token}`
        }
      });

      return next.handle(request).pipe(tap((event: HttpEvent<any>) => {}, err => {
        if (err instanceof HttpErrorResponse && err.status === 401) {

        }
      }));
    }

    return next.handle(request).pipe(tap((event: HttpEvent<any>) => {}, err => {
      if (err instanceof HttpErrorResponse && err.status === 401) {

      }
    }));
  }
}
