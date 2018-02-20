import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class PatientListServiceService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders(/*{'Content-Type': 'application/json'}*/)
    };
  }

  /* get all patient */
  getPatients() {
    /*TODO add good url */
    return this.http.get('http://localhost:8080/back-1.0-SNAPSHOT/rs/patient', this.httpOptions).toPromise();
  }

}
