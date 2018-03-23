import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';
import {TokenService} from '../token.service';

@Injectable()
export class AdministrationRequestService {

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  getAdminFilePatient(idPatient) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });
    return this.http.get(util.format(Constants.GET_ADMIN_FILE_URL, idPatient),
      {headers: headers}).toPromise();
  }
}
