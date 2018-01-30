import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-administration-file',
  templateUrl: './administration-file.component.html',
  styleUrls: ['./administration-file.component.css']
})
export class AdministrationFileComponent implements OnInit {
  lastName: string;
  firstName: string;
  sexe: string;
  birthday: string;
  birthplace: string;
  address: string;
  zipcode: string;
  city: string;
  country: string;
  addressComplement: string;
  email: string;
  fixe: string;
  cellular: string;
  fax: string;
  healthCard: string;
  socialSecurity: string;

  constructor() {


  }

  ngOnInit() {
  }

}
