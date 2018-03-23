import {Injectable} from '@angular/core';

@Injectable()
export class TokenService {
  token;

  getToken() {
    return this.token;
  }

  setToken(token) {
    this.token = token;
  }

}
