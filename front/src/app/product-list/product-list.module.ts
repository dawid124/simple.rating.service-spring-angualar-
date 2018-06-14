import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './components/product-list/product-list.component';
import {TranslateModule} from '@ngx-translate/core';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';
import {GlobalModule} from '../global/global.module';
import {MatInputModule} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    InfiniteScrollModule,
    TranslateModule,
    GlobalModule,
    FormsModule,
    MatInputModule
  ],
  declarations: [ProductListComponent]
})
export class ProductListModule { }
