import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})


export class LoginPageComponent implements OnInit {

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

}
