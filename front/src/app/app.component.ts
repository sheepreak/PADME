import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {UserService} from './user.service';

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

  user: UserService;

  constructor(private translate: TranslateService, private userService: UserService) {
    this.user = userService;
    const language = localStorage.getItem('language');
    if (language) {
      this.switchLanguage(language);
    } else {
      translate.setDefaultLang('fr');
    }
  }

  switchLanguage(language: string) {
    this.translate.use(language);
    localStorage.setItem('language', language);
  }
}

