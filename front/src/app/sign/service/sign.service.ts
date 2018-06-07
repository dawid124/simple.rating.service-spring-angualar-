import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {api, environment} from '../../../environments/environment';
import {UserData} from '../../models/user-data';
import {tap} from 'rxjs/operators';
import {AuthService} from '../../auth/auth.service';
import {Registration} from '../../models/registration';
import {Login} from '../../models/login';

@Injectable({
  providedIn: 'root'
})
export class SignService {

  constructor(private httpClient: HttpClient,
              private authService: AuthService) { }

  login(loginModel: Login): Observable<any> {
    return this.httpClient.post(`${environment.SERVER_ADDRESS}${api.AUTH.LOGIN}`, loginModel, {responseType: 'text'})
      .pipe(tap(jwt => this.handleJwtResponse(jwt)));
  }

  logout() {
    this.authService.removeTokenFromStorage()
      .subscribe(() => this.authService.clearData() );
  }

  signup(registrationModel: Registration): Observable<any> {
    return this.httpClient.post(`${environment.SERVER_ADDRESS}${api.AUTH.REGISTRATION}`, registrationModel, {responseType: 'text'})
      .pipe(tap(jwt => {
        if (jwt !== 'EXISTS') {
          return this.handleJwtResponse(jwt);
        }
        return jwt;
      }));
  }

  private handleJwtResponse(token: string) {
    return this.authService.setTokenFromStorage(token);
  }
}
