import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {UserService} from '../user.service';
import {Constants} from '../../constants';
import * as util from 'util';

@Injectable()
export class MedicalDocService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }

  /* get all documents */
  getDocuments(id) {
    return this.http.get(util.format(Constants.GET_MEDICAL_FILE_URL, id), {headers: this.httpOptions}).toPromise();
  }
}
