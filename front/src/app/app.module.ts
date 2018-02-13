import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule} from '@angular/forms'; // <-- NgModel lives here

import {AppComponent} from './app.component';
import {StaticNavBarComponent} from './static-nav-bar/static-nav-bar.component';
import {DynamicNavBarComponent} from './dynamic-nav-bar/dynamic-nav-bar.component';
import {LoginPageComponent} from './login-page/login-page.component';
import {UserService} from './user.service';
import {WebApiPromiseService} from './web-api-promise.service';
import {AdministrationFileComponent} from './administration-file/administration-file.component';
import {PrescriptionFileComponent} from './prescription-file/prescription-file.component';
import {PatientListComponent, SearchPipe} from './patient-list/patient-list.component';
import {ExamenFileComponent} from './examen-file/examen-file.component';
import {ConsultationFileComponent} from './consultation-file/consultation-file.component';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {PatientListComponent , SearchPipe} from './patient-list/patient-list.component';
import { ExamenFileComponent } from './examen-file/examen-file.component';
import { ConsultationFileComponent } from './consultation-file/consultation-file.component';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AdminViewComponent } from './admin-view/admin-view.component';


import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

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
    path: 'patientlist',
    component: PatientListComponent
  },
  {
    path: 'examenfile',
    component: ExamenFileComponent
  },
  {
    path: 'consultationfile',
    component: ConsultationFileComponent
  },
  {
    path: '**',
    redirectTo: 'login'
  },
  {
    path: 'adminview',
    component: AdminViewComponent
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
    ExamenFileComponent,
    PrescriptionFileComponent,
    PatientListComponent,
    SearchPipe,
    ConsultationFileComponent,
    AdminViewComponent
  ],

  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    ),
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    )
  ],
  providers: [UserService, WebApiPromiseService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
