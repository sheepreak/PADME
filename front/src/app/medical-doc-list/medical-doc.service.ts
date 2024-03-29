import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserService} from '../user.service';
import {Constants} from '../../constants';
import * as util from 'util';
import {TokenService} from '../token.service';

@Injectable()
export class MedicalDocService {

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  /* get all documents */
  getDocuments(id) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(util.format(Constants.GET_MEDICAL_FILE_URL, id), {headers: headers}).toPromise();
  }
}
