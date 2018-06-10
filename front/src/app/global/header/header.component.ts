import {Component, OnInit, ViewChild} from '@angular/core';
import {PopupComponent} from '../popup/popup.component';
import {AuthService} from '../../auth/auth.service';
import {Role} from '../../models/role';
import {SignService} from '../../sign/service/sign.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @ViewChild('signPopup') signPopup: PopupComponent;

  constructor(private authService: AuthService,
              private signService: SignService) { }

  ngOnInit() {
  }

  showSignPopup() {
    this.signPopup.show();
  }

  hideSignPopup() {
    this.signPopup.hide();
  }

  logout() {
    this.signService.logout();
  }

  goToNewProduct() {

  }

  isLogged(): boolean {
    return this.authService.userData !== undefined && this.authService.userData !== null;
  }

  isAdmin(): boolean {
    if (!this.authService.userData) {
      return false;
    }

    return this.authService.userData.role === Role.ROLE_ADMIN;
  }
}
