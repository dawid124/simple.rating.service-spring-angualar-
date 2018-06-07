import {Injectable} from '@angular/core';
import {api, environment} from '../../environments/environment';
import {UserData} from '../models/user-data';
import {LocalStorage} from '@ngx-pwa/local-storage';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public static token: string;
  private _userData: UserData;

  constructor(private storage: LocalStorage,
              private httpClient: HttpClient) {
  }

  getTokenFromStorage(): Observable<string> {
    return this.storage.getItem(environment.JWT_TOKEN);
  }

  setTokenFromStorage(token: string): void {
    this.storage.setItem(environment.JWT_TOKEN, token)
      .subscribe(() => token);
  }

  removeTokenFromStorage(): Observable<any> {
    return this.storage.removeItem(environment.JWT_TOKEN);
  }

  loadUserData(): void {
    this.checkPrivilege().subscribe(
      (userData: UserData) => {
        this._userData = userData;
      }, (err) => {
        this._userData = null;
      });
  }

  checkPrivilege(): Observable<UserData> {
    return this.httpClient.get<UserData>(`${environment.SERVER_ADDRESS}${api.AUTH.AUTHENTICATE}`);
  }

  clearData(): void {
    this.userData = null;
    AuthService.token = null;
  }

  get userData(): UserData {
    return this._userData;
  }

  set userData(value: UserData) {
    this._userData = value;
  }
}