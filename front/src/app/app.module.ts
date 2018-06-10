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
import {RouterModule} from '@angular/router';
import {ProductComponent} from './product/component/product/product.component';
import {ProductModule} from './product/product.module';
import {AppRoutingModule} from './app-routing-module';
import {ProductService} from './product/service/product.service';
import {AuthService} from './auth/auth.service';


export function configFactory(authService: AuthService) {
  return  () => authService.initializeUserData();
}


@NgModule({
  declarations: [
    AppComponent,
    PopupComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    SignModule,
    NgxSpinnerModule,
    HttpClientModule,
    ProductModule,
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
