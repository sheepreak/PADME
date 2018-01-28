import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';

import {WebApiPromiseService} from '../web-api-promise.service';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})


export class LoginPageComponent implements OnInit {

  errorLogin = null;

  constructor(private userService: UserService, private requester: WebApiPromiseService) {
  }

  ngOnInit() {
  }

  onSubmit(form) {

    console.log(form);

    if (form.login != null && form.password != null) {
      this.requester.connectUser(form.email, form.password).then(data => {
        this.userService.connect(form.email, form.password);
        this.errorLogin = null;
      }).catch(err => {
        console.log(err);
        this.errorLogin = 'An error has occurred';
      });
    } else {
      this.errorLogin = 'Login and password required';
    }
  }

}
