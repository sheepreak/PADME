import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {WebApiPromiseService} from './web-api-promise.service';


@Injectable()
export class PatientService {

  private firstName: string;
  private lastName: string;

  constructor(private requester: WebApiPromiseService, private router: Router) {
  }

  setPatient(firstName: string, lastName: string){
    this.firstName = firstName;
    this.lastName = lastName;
  }

  getfirstName(): string {
    return this.firstName;
  }

  getlastName(): string {
    return this.lastName;
  }

}
