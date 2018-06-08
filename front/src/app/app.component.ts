import {Component, OnInit, ViewChild} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {AuthService} from './auth/auth.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {PopupComponent} from './global/popup/popup.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  @ViewChild('signPopup') signPopup: PopupComponent;

  constructor(private translate: TranslateService,
              private spinner: NgxSpinnerService,
              private authService: AuthService) {
    this.initializeMultilanguage();
    this.initializeUserData();
  }
  ngOnInit(): void {

  }

  showSignPopup() {
      this.signPopup.show();
  }

  hideSignPopup() {
    this.signPopup.hide();
  }

  initializeMultilanguage() {
    this.translate.addLangs(['pl', 'en']);
    this.translate.setDefaultLang('pl');

    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/pl|en/) ? browserLang : 'pl');
  }

  private initializeUserData() {
    this.authService.getTokenFromStorage()
      .subscribe((token: string) => {
        AuthService.token = token;
      });

    if (AuthService.token) {
      this.authService.loadUserData();
    }
  }
}
