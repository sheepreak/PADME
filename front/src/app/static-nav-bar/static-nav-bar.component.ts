import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {UserService} from '../user.service';
import {TranslateService} from '@ngx-translate/core';
import {AppComponent} from '../app.component';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';


@Component({
  selector: 'app-static-nav-bar',
  templateUrl: './static-nav-bar.component.html',
  styleUrls: ['./static-nav-bar.component.css']
})
export class StaticNavBarComponent implements OnInit {

  name: string;
  role: string;
  visibility: boolean;

  constructor(private userService: UserService) {
    this.name = this.userService.getlastName() + ' ' + this.userService.getfirstName();
    this.role = this.userService.getRole();
    this.visibility = false;

    /*TODO Remove this */
    this.name = 'NOM prenom';
    this.role = 'Docteur';
  }

  ngOnInit(): void {

  }

  public loggout() {
    this.userService.loggout();
  }

  public changeVisibility() {
    this.visibility = !this.visibility;
  }
}




