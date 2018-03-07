import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable()
export class MedicalDocService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  /* get all documents */
  getDocuments() {
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/medicalFile/72', {headers: this.httpOptions}).toPromise();
  }
}
