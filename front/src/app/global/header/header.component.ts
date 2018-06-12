import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {PopupComponent} from '../popup/popup.component';
import {AuthService} from '../../auth/auth.service';
import {Role} from '../../models/role';
import {SignService} from '../../sign/service/sign.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @ViewChild('signPopup') signPopup: PopupComponent;

  @Input('productName') productName: string;
  @Input('productId') productId: number;

  constructor(private authService: AuthService,
              private router: Router,
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

  isLogged(): boolean {
    return this.authService.userData !== undefined && this.authService.userData !== null;
  }

  isAdmin(): boolean {
    if (!this.authService.userData) {
      return false;
    }

    return this.authService.userData.role === Role.ROLE_ADMIN;
  }

  isActive(instruction: any[]): boolean {
    return this.router.isActive(this.router.createUrlTree(instruction), false);
  }
}
