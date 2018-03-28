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


  createNode(idNode, node) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    return this.http.put(util.format(Constants.ADD_NODE_URL, idNode),
      node, {headers: headers}).toPromise();
  }


  getNode() {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    return this.http.get(util.format(Constants.GET_NODE_URL), {headers: headers}).toPromise();
  }

  createNodePole(idHospital, node) {

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': this.tokenService.getToken()
    });

    return this.http.put(util.format(Constants.PUT_NODE_POLE, idHospital),
      node, {headers: headers}).toPromise();
  }
}
