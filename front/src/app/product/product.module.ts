import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductComponent} from './component/product/product.component';
import {MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatTabsModule} from '@angular/material';
import {TranslateModule} from '@ngx-translate/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditProductComponent} from './component/product-edit/edit-product.component';
import {PreviewProductComponent} from './component/product-preview/preview-product.component';
import {NewTypePopupComponent} from './component/include/new-type-popup/new-type-popup.component';
import {GalleryComponent} from './component/include/gallery/gallery.component';
import {RatingComponent} from './component/include/rating/rating.component';
import {CommentsComponent} from './component/include/comments/comments.component';
import {GlobalModule} from '../global/global.module';

@NgModule({
  imports: [
    CommonModule,
    MatTabsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    GlobalModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    TranslateModule
  ],
  declarations: [
    ProductComponent,
    EditProductComponent,
    PreviewProductComponent,
    NewTypePopupComponent,
    GalleryComponent,
    RatingComponent,
    CommentsComponent
  ],
  entryComponents: [
    NewTypePopupComponent
  ]
})
export class ProductModule {
}
