import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Constants} from '../../constants';
import * as util from 'util';
import {TokenService} from '../token.service';


@Injectable()
export class AdminViewRequestService {
  constructor(private http: HttpClient, private tokenService: TokenService) {
  }


  updateNodeStaff(idStaff, node) {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    return this.http.put(util.format(Constants.UPDATE_NODE_STAFF_URL, idStaff),
      node, {headers: headers}).toPromise();
  }

}
