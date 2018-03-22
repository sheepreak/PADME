import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';

@Injectable()
export class AdministrationRequestService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  getAdminFilePatient(idPatient) {
    return this.http.get(util.format(Constants.GET_ADMIN_FILE_URL, idPatient),
      {headers: this.httpOptions}).toPromise();
  }
}
