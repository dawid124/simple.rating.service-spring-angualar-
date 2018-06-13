import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupComponent } from './popup/popup.component';
import { HeaderComponent } from './header/header.component';
import {TranslateModule} from '@ngx-translate/core';
import {SignModule} from '../sign/sign.module';
import {MatButtonModule} from '@angular/material';
import { NotFoundComponent } from './not-found/not-found.component';
import {RouterModule} from '@angular/router';
import { ErrorModelComponent } from './error-model/error-model.component';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    SignModule,
    RouterModule,
    MatButtonModule
  ],
  exports: [
    PopupComponent,
    HeaderComponent,
    ErrorModelComponent
  ],
  declarations: [PopupComponent, HeaderComponent, NotFoundComponent, ErrorModelComponent]
})
export class GlobalModule { }
