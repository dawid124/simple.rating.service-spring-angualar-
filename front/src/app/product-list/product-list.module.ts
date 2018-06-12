import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductListComponent} from './product-list/product-list.component';
import {TranslateModule} from '@ngx-translate/core';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';
import {GlobalModule} from '../global/global.module';

@NgModule({
  imports: [
    CommonModule,
    InfiniteScrollModule,
    TranslateModule,
    GlobalModule
  ],
  declarations: [ProductListComponent]
})
export class ProductListModule { }
