import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

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
    return this.http.post('http://localhost:8080/back-1.0-SNAPSHOT/rs/staff/connect', body, {headers: this.httpOptions}).toPromise();
  }


}
