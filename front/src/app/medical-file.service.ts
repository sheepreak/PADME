import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class MedicalFileService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }


  getDoctor(id) {
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/staff/' + id, {headers: this.httpOptions}).toPromise();
  }

}
