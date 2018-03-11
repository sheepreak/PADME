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


  getPatients(id) {
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/staff/patients/' + id, {headers: this.httpOptions}).toPromise();
  }

  getAllPatients() {
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient', {headers: this.httpOptions}).toPromise();
  }
}
