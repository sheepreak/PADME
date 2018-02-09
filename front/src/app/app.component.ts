import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

/*
npm install @ngx-translate/core --save
npm install @ngx-translate/http-loader
 */

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private translate: TranslateService) {
    translate.setDefaultLang('fr');
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }
}

