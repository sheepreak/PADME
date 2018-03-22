import {ElementRef, Injectable, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';

@Injectable()
export class PatientListServiceService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }


  getPatients(id) {
    return this.http.get(util.format(Constants.GET_PATIENTS_BY_DOCTOR_URL, id), {headers: this.httpOptions}).toPromise();
  }

  getAllPatients() {
    return this.http.get(Constants.GET_ALL_PATIENTS, {headers: this.httpOptions}).toPromise();
  }
}
