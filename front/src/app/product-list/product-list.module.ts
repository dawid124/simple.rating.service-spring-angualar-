import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductListComponent } from './product-list/product-list.component';
import {TranslateModule} from '@ngx-translate/core';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';

@NgModule({
  imports: [
    CommonModule,
    InfiniteScrollModule,
    TranslateModule
  ],
  declarations: [ProductListComponent]
})
export class ProductListModule { }
