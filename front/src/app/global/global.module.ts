import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupComponent } from './popup/popup.component';
import { HeaderComponent } from './header/header.component';
import {TranslateModule} from '@ngx-translate/core';
import {SignModule} from '../sign/sign.module';
import {MatButtonModule} from '@angular/material';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    SignModule,
    MatButtonModule
  ],
  exports: [
    PopupComponent,
    HeaderComponent
  ],
  declarations: [PopupComponent, HeaderComponent, NotFoundComponent]
})
export class GlobalModule { }
