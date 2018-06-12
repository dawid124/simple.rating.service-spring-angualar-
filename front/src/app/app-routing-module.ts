import {ProductComponent} from './product/component/product/product.component';
import {Route, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {ProductListComponent} from './product-list/product-list/product-list.component';

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'products'},
  {path: 'product', component: ProductComponent},
  {path: 'products', component: ProductListComponent},
  {path: 'product/:id', component: ProductComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
