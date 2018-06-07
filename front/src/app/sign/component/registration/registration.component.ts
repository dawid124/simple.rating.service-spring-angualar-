import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EMAIL_PATTERN} from '../../../../environments/environment';
import {finalize} from 'rxjs/operators';
import {Registration} from '../../../models/registration';
import {NgxSpinnerService} from 'ngx-spinner';
import {SignService} from '../../service/sign.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private signService: SignService,
              private spinner: NgxSpinnerService) { }

  ngOnInit() {
    this.registrationForm = this.buildRegistrationForm();
  }

  buildRegistrationForm() {
    return this.formBuilder.group({
      username: ['', Validators.required],
      email: ['',
        [ Validators.pattern(EMAIL_PATTERN),
          Validators.required]],
      password: ['', Validators.required],
      repassword: ['', Validators.required]
    });
  }

  signup() {
    const registration: Registration = {
      username: this.username.value,
      email: this.email.value,
      password: this.password.value
    };

    this.spinner.show();

    this.signService
      .signup(registration)
      .pipe(finalize(() => this.spinner.hide()))
      .subscribe(
        () => {

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

  get username() {
    return this.registrationForm.get('username');
  }

  get email() {
    return this.registrationForm.get('email');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get repassword() {
    return this.registrationForm.get('repassword');
  }
}
