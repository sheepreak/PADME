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
    /*TODO add good url */
    return this.http.post('', body, this.httpOptions).toPromise();
  }


  /* get all patient */
  getPatient() {
    /*TODO add good url */
    return this.http.get('', this.httpOptions).toPromise();
  }

}
