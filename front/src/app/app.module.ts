import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SignModule} from './sign/sign.module';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PopupComponent} from './global/popup/popup.component';
import {NgxSpinnerModule} from 'ngx-spinner';
import {AuthInterceptor} from './auth/auth.interceptor';
import {HeaderComponent} from './global/header/header.component';
import {ProductModule} from './product/product.module';
import {AppRoutingModule} from './app-routing-module';
import {AuthService} from './auth/auth.service';
import {ProductListModule} from './product-list/product-list.module';
import {NotFoundComponent} from './global/not-found/not-found.component';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';


export function configFactory(authService: AuthService) {
  return  () => authService.initializeUserData();
}


@NgModule({
  declarations: [
    AppComponent,
    PopupComponent,
    HeaderComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SignModule,
    NgxSpinnerModule,
    HttpClientModule,
    ProductModule,
    InfiniteScrollModule,
    ProductListModule,
    AppRoutingModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS,  useClass: AuthInterceptor, multi: true },
    { provide: APP_INITIALIZER,  useFactory: configFactory, deps: [AuthService], multi: true }
  ],
  bootstrap: [AppComponent],
  exports: [
    TranslateModule
  ]
})
export class AppModule { }


export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient, './assets/i18n/', '.json');
}
