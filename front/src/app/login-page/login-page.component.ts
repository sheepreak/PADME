import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';

import {WebApiPromiseService} from '../web-api-promise.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css', './../app.component.css']
})


export class LoginPageComponent implements OnInit {
  errorLogin = null;

  constructor(private userService: UserService, private requester: WebApiPromiseService, private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit(form) {
    if (form.login != null && form.password != null) {
      this.requester.connectUser(form.login, form.password).then(data => {
        this.userService.connect(form.login, form.password, data);
        this.errorLogin = null;
        if (this.userService.getStatus() === 'DOCTOR') {
          this.router.navigate(['/patientlist']);
        } else if (this.userService.getStatus() === 'SECRETAIRE') {
          this.router.navigate(['/patientlist']);
        } else if (this.userService.getStatus() === 'ADMIN') {
          this.router.navigate(['/adminview']);
        } else {
          this.router.navigate(['/patientlist']);
        }

      }).catch(err => {
        if (err.status === 401) {
          this.errorLogin = 'Login or password is false';
        } else {
          this.errorLogin = 'An error has occurred';
        }

      });
    } else {
      this.errorLogin = 'Login and password required';
    }
  }

}
