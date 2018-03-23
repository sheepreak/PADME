import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../constants';
import {TokenService} from './token.service';

@Injectable()
export class WebApiPromiseService {

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  /* call to connect user */
  connectUser(login, password) {
    const h = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    const body = {
      'login': login,
      'password': password
    };
    return this.http.post(Constants.CONNECTION_URL, body, {headers: h}).toPromise();
  }


  getStaffs() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    return this.http.get(Constants.GET_STAFF_URL, {headers: headers}).toPromise();
  }

  updateStaff(body) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.put(Constants.UPDATE_STAFF_SOCIO_URL, body, {headers: headers}).toPromise();
  }

  getHospitals() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(Constants.GET_HOSPITALS_URL, {headers: headers}).toPromise();
  }
}
