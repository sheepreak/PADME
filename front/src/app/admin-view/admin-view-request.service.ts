import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AdminViewRequestService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }


  updateNodeStaff(idStaff, node) {
    return this.http.put('http://localhost:8080/back-1.0-SNAPSHOT/rs/staff/' + idStaff + '/' + 'node',
      node, {headers: this.httpOptions}).toPromise();
  }

}
