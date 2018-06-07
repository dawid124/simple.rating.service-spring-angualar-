import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupComponent } from './popup/popup.component';

@NgModule({
  imports: [
    CommonModule
  ],
  exports: [
    PopupComponent
  ],
  declarations: [PopupComponent]
})
export class GlobalModule { }
