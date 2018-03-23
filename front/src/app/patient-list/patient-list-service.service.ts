import {ElementRef, Injectable, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';
import {TokenService} from '../token.service';

@Injectable()
export class PatientListServiceService {
  constructor(private http: HttpClient, private tokenService: TokenService) {
  }


  getPatients(id) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(util.format(Constants.GET_PATIENTS_BY_DOCTOR_URL, id), {headers: headers}).toPromise();
  }

  getAllPatients() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(Constants.GET_ALL_PATIENTS, {headers: headers}).toPromise();
  }
}
