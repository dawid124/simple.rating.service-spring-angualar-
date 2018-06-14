import {ProductComponent} from './product/component/product/product.component';
import {Route, RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {ProductListComponent} from './product-list/components/product-list/product-list.component';
import {NotFoundComponent} from './global/not-found/not-found.component';

const APP_ROUTES: Route[] = [
  {path: '', pathMatch: 'full', redirectTo: 'products'},
  {path: 'product/:edit', component: ProductComponent},
  {path: 'products', component: ProductListComponent},
  {path: 'product-details/:id', component: ProductComponent},
  {path: 'product-details/:id/:edit', component: ProductComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(APP_ROUTES)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
