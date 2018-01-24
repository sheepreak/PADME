import {Injectable} from '@angular/core';

@Injectable()
export class UserService {
  connected: boolean;
  login: string;
  token: string;

  connect(login, token) {
    this.connected = true;
    localStorage.setItem('user', JSON.stringify(
      {
        'login': login,
        'token': token
      }));
  }

  isConnected() {
    return this.connected;
  }


  loggout() {
    this.login = null;
    this.token = null;
    this.connected = false;
    localStorage.clear();
  }

  getToken() {
    return this.token;
  }

  getLogin() {
    return this.login;
  }


}
