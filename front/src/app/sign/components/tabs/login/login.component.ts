import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgxSpinnerService} from 'ngx-spinner';
import {SignService} from '../../../service/sign.service';
import {finalize} from 'rxjs/operators';
import {Login} from '../../../../models/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  @Output() hidePopup: EventEmitter<any> = new EventEmitter();

  constructor(private formBuilder: FormBuilder,
              private signService: SignService,
              private spinner: NgxSpinnerService) { }

  ngOnInit() {
    this.loginForm = this.buildLoginForm();
  }

  signin() {
    const login: Login = {
      username: this.username.value,
      password: this.password.value
    };

    this.spinner.show();

    this.signService
      .signin(login)
      .pipe(finalize(() => this.spinner.hide()))
      .subscribe(
        () => {
          this.hidePopup.emit();
        },
        (err) => {
          this.handleError(err);
        });

  }

  handleError(error: any) {
    const errorsArray = {
      409: 'SING_UP.MESSAGES.ALREADY_EXISTS',
      403: 'SING_UP.MESSAGES.EMPTY_DEVICE_ID',
      400: 'SING_UP.MESSAGES.FORM_NOT_VALID',
    };

    let messageKey = errorsArray[error.status];

    if (!messageKey) {
      messageKey = 'SERVER_ERROR_MESSAGE';
    }
    // this.popUps.createToastWithBlackBackground(this.toastCtrl, messageKey);
  }

  buildLoginForm() {
    return this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

}
