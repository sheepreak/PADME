import {Injectable} from '@angular/core';

@Injectable()
export class UserService {

  private connected: boolean;
  private login: string;
  private token: string;
  private _firstName: string;
  private _lastName: string;
  private role: string;

  connect(login, user) {
    this.connected = true;
    this.login = login;
    this.token = user.token;
    this._firstName = user.firstName;
    this._lastName = user._firstName;

    this.role = user.role;

    localStorage.setItem('user', JSON.stringify(
      {
        'login': login,
        'token': user.token,
        'firstName': user._firstName,
        'lastName': user._lastName
      }));


  }

  isConnected() {
    return this.connected;
  }


  loggout() {
    this.login = null;
    this.token = null;
    this._firstName = null;
    this._lastName = null;
    this.connected = false;
    localStorage.clear();
  }

  getToken() {
    return this.token;
  }

  getLogin() {
    return this.login;
  }

  getfirstName(): string {
    return this._firstName;
  }

  getlastName(): string {
    return this._lastName;
  }

  getRole() {
    return this.role;
  }


}
