import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../constants';

@Injectable()
export class WebApiPromiseService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  /* call to connect user */
  connectUser(login, password) {
    const body = {
      'login': login,
      'password': password
    };
    return this.http.post(Constants.CONNECTION_URL, body, {headers: this.httpOptions}).toPromise();
  }


  getStaffs() {
    return this.http.get(Constants.GET_STAFF_URL, {headers: this.httpOptions}).toPromise();
  }

  updateStaff(body) {
    return this.http.put(Constants.UPDATE_STAFF_SOCIO_URL, body, {headers: this.httpOptions}).toPromise();
  }

  getHospitals() {
    return this.http.get(Constants.GET_HOSPITALS_URL, {headers: this.httpOptions}).toPromise();
  }
}
