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
    this.address = "4 allée des camélias";
    this.addressComplement = "Aucun";
    this.birthday = "12/06/1995";
    this.birthplace = "Lagny";
    this.cellular = "06.74.18.98.16";
    this.city = "Noisy le grand";
    this.country = "France";
    this.email = "amelinemoreau@orange.fr";
    this.fax = "Aucun";
    this.firstName = "Ameline";
    this.healthCard = "77642213543213";
    this.lastName = "Moreau";
    this.sexe = "F";
    this.socialSecurity = "Aucun";
    this.zipcode = "77164";
    this.fixe = "01.64.64.15.98";

  }

  ngOnInit() {
  }

}
