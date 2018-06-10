import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RegistrationComponent} from './components/tabs/registration/registration.component';
import {LoginComponent} from './components/tabs/login/login.component';
import {SignComponent} from './components/tabs/sign.component';
import {TranslateModule} from '@ngx-translate/core';
import {MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatTabsModule} from '@angular/material';
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
    MatDialogModule,

    NgxSpinnerModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    TranslateModule
  ],
  exports: [
    SignComponent,
    RegistrationComponent,
    LoginComponent
  ],
  declarations: [RegistrationComponent, LoginComponent, SignComponent],
  entryComponents: [RegistrationComponent]
})
export class SignModule { }
