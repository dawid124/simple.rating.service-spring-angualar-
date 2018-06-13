import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NgxSpinnerService} from 'ngx-spinner';
import {SignService} from '../../../service/sign.service';
import {finalize} from 'rxjs/operators';
import {Login} from '../../../../models/login';
import {AuthService} from '../../../../auth/auth.service';
import {UserData} from '../../../../models/user-data';
import {ErrorModelComponent} from '../../../../global/error-model/error-model.component';
import {MatDialog} from '@angular/material';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  @Output() hidePopupEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private formBuilder: FormBuilder,
              private signService: SignService,
              private dialog: MatDialog,
              private authService: AuthService,
              private spinner: NgxSpinnerService) {
  }

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
          this.hidePopup();
        },
        (err) => {
          this.handleError(err);
        });

  }

  handleError(error: any) {
    const errorsArray = {
      400: 'SING_UP.MESSAGES.FORM_NOT_VALID',
    };

    let messageKey = errorsArray[error.status];

    if (!messageKey) {
      messageKey = 'SERVER_ERROR_MESSAGE';
    }

    this.dialog.open(ErrorModelComponent, {
      data: {
        errorCode: messageKey
      }
    });
  }

  buildLoginForm() {
    return this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  hidePopup() {
    this.hidePopupEmitter.emit();
  }

  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

}
