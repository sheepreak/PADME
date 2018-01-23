import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms'; // <-- NgModel lives here
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {StaticNavBarComponent} from './static-nav-bar/static-nav-bar.component';
import {DynamicNavBarComponent} from './dynamic-nav-bar/dynamic-nav-bar.component';
import {LoginPageComponent} from './login-page/login-page.component';
import { UserService } from './user.service';


const appRoutes: Routes = [
  {
    path: 'login',
    component : LoginPageComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    StaticNavBarComponent,
    DynamicNavBarComponent,
    LoginPageComponent,
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
