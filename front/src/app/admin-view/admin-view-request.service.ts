import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';


@Injectable()
export class AdminViewRequestService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'})
    };
  }


  updateNodeStaff(idStaff, node) {
    return this.http.put(util.format(Constants.UPDATE_NODE_STAFF_URL, idStaff),
      node, {headers: this.httpOptions}).toPromise();
  }

}
