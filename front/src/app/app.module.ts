import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms'; // <-- NgModel lives here
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {StaticNavBarComponent} from './static-nav-bar/static-nav-bar.component';
import {DynamicNavBarComponent} from './dynamic-nav-bar/dynamic-nav-bar.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {UserService} from './user.service';
import {WebApiPromiseService} from './web-api-promise.service';
import {AdministrationFileComponent} from './administration-file/administration-file.component';
import {PrescriptionFileComponent} from './prescription-file/prescription-file.component';
import {PatientListComponent} from './patient-list/patient-list.component';


const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'administrationfile',
    component: AdministrationFileComponent
  },
  {
    path: 'prescriptionfile',
    component: PrescriptionFileComponent
  },
  {
    path: 'patientlistcomponent',
    component: PatientListComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    StaticNavBarComponent,
    DynamicNavBarComponent,
    LoginPageComponent,
    AdministrationFileComponent,
    PrescriptionFileComponent,
    PatientListComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    )
  ],
  providers: [UserService, WebApiPromiseService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
