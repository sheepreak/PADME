import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as util from 'util';
import {Constants} from '../constants';
import {TokenService} from './token.service';

@Injectable()
export class MedicalFileService {
  constructor(private http: HttpClient, private tokenService: TokenService) {
  }


  getDoctor(id) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(util.format(Constants.GET_STAFF_BY_ID_URL, id), {headers: headers}).toPromise();
  }

}
