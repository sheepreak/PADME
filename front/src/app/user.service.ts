import {Injectable} from '@angular/core';

@Injectable()
export class UserService {
  login: string;
  token: string;
  connected: boolean;

  isConnected() {
    return this.connected;
  }


}
