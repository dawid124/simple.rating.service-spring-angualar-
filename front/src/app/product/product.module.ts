import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ProductComponent} from './component/product/product.component';
import {MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatTabsModule} from '@angular/material';
import {TranslateModule} from '@ngx-translate/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditProductComponent} from './component/edit-product/edit-product.component';
import {PreviewProductComponent} from './component/preview-product/preview-product.component';
import {NewTypePopupComponent} from './component/new-type-popup/new-type-popup.component';
import {GalleryComponent} from './component/gallery/gallery.component';
import {RatingComponent} from './component/rating/rating.component';
import {CommentsComponent} from './component/comments/comments.component';

@NgModule({
  imports: [
    CommonModule,
    MatTabsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatFormFieldModule,
    MatButtonModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDialogModule,
    TranslateModule
  ],
  declarations: [ProductComponent, EditProductComponent, PreviewProductComponent, NewTypePopupComponent, GalleryComponent, RatingComponent, CommentsComponent],
  entryComponents: [NewTypePopupComponent]
})
export class ProductModule {
}
