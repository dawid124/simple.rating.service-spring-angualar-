import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EMAIL_PATTERN} from '../../../../../environments/environment';
import {finalize} from 'rxjs/operators';
import {Registration} from '../../../../models/registration';
import {NgxSpinnerService} from 'ngx-spinner';
import {SignService} from '../../../service/sign.service';
import {MatDialog} from '@angular/material';
import {ErrorModelComponent} from '../../../../global/error-model/error-model.component';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationForm: FormGroup;

  @Output() switchTab: EventEmitter<number> = new EventEmitter();
  @Output() hidePopupEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private formBuilder: FormBuilder,
              private signService: SignService,
              private dialog: MatDialog,
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
          this.switchTab.emit(0);
        },
        (err) => {
          this.handleError(err);
        });
  }

  handleError(error: any) {
    const errorsArray = {
      409: 'ERRORS.ALREADY_EXISTS',
      400: 'ERRORS.FORM_NOT_VALID',
    };

    let messageKey = errorsArray[error.status];

    if (!messageKey) {
      messageKey = 'ERRORS.SERVER_ERROR_MESSAGE';
    }

    this.dialog.open(ErrorModelComponent, {
      data: {
        errorCode: messageKey
      }
    });
  }

  hidePopup() {
    this.hidePopupEmitter.emit();
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
