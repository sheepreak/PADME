import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {UserService} from '../user.service';

@Component({
  selector: 'app-static-nav-bar',
  templateUrl: './static-nav-bar.component.html',
  styleUrls: ['./static-nav-bar.component.css']
})
export class StaticNavBarComponent implements OnInit {

  name: string;
  role: string;

  constructor(private userService: UserService) {
    this.name = this.userService.getlastName() + ' ' + this.userService.getfirstName();
    this.role = this.userService.getRole();

    /*TODO Remove this */
    this.name = 'NOM prenom';
    this.role = 'Docteur';
  }

  ngOnInit(): void {

  }

}
