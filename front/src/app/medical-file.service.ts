import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as util from 'util';
import {Constants} from '../constants';

@Injectable()
export class MedicalFileService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }


  getDoctor(id) {
    return this.http.get(util.format(Constants.GET_STAFF_BY_ID_URL, id), {headers: this.httpOptions}).toPromise();
  }

}
