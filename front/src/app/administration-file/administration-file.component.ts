import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-administration-file',
  templateUrl: './administration-file.component.html',
  styleUrls: ['./administration-file.component.css']
})
export class AdministrationFileComponent implements OnInit {
  directory = {
    'lastName': '',
    'firstName': '',
    'sexe': '',
    'birthday': new Date(),
    'birthplace': '',
    'address': '',
    'zipcode': '',
    'city': '',
    'country': '',
    'addressComplement': '',
    'email': '',
    'fixe': '',
    'cellular': '',
    'fax': '',
    'healthCard': '',
    'socialSecurity': ''
  };

  modifyData = false;
  oldDirectory;

  constructor() {
    this.directory.address = '4 allée des camélias';
    this.directory.addressComplement = 'Aucun';
    this.directory.birthday = new Date('12/06/1995');
    this.directory.birthplace = 'Lagny';
    this.directory.cellular = '06.74.18.98.16';
    this.directory.city = 'Noisy le grand';
    this.directory.country = 'France';
    this.directory.email = 'amelinemoreau@orange.fr';
    this.directory.fax = 'Aucun';
    this.directory.firstName = 'Ameline';
    this.directory.healthCard = '77642213543213';
    this.directory.lastName = 'Moreau';
    this.directory.sexe = 'F';
    this.directory.socialSecurity = 'Aucun';
    this.directory.zipcode = '77164';
    this.directory.fixe = '01.64.64.15.98';

  }

  ngOnInit() {
  }


  ModifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    this.modifyData = true;
  }


  cancelModif() {
    console.log(this.oldDirectory);
    this.directory = this.oldDirectory;
    this.modifyData = false;

  }

}
