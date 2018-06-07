import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegistrationComponent} from './component/registration/registration.component';
import {LoginComponent} from './component/login/login.component';
import {SignComponent} from './component/tabs/sign.component';
import {TranslateModule} from '@ngx-translate/core';
import {MatButtonModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatTabsModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgxSpinnerModule} from 'ngx-spinner';

@NgModule({
  imports: [
    MatTabsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,

    NgxSpinnerModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    TranslateModule
  ],
  exports: [
    SignComponent
  ],
  declarations: [RegistrationComponent, LoginComponent, SignComponent]
})
export class SignModule { }
