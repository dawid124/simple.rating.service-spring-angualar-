import {ProductComponent} from './product/component/product/product.component';
import {Route, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'products'},
  {path: 'product', component: ProductComponent},
  // {path: 'product/:id', component: ProductComponent}
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
