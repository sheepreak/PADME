import {ElementRef, Injectable, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class PatientListServiceService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  /* get all patient */
  getPatients() {
    //return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient', {headers: this.httpOptions}).toPromise();
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/staff/patients/144', {headers: this.httpOptions}).toPromise();
  }
}
