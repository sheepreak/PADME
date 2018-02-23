import {Component, OnInit} from '@angular/core';
import {ManageFile} from '../manageFile';
import {UserService} from '../user.service';
import {AdministrationRequestService} from './administration-request.service';


@Component({
  selector: 'app-administration-file',
  templateUrl: './administration-file.component.html',
  styleUrls: ['./administration-file.component.css', './../app.component.css']
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
    'socialSecurity': '',
    'profession': '',
    'specialisation': ''
  };

  isEmploye = true;
  oldDirectory;
  manageFile: ManageFile = new ManageFile();

  constructor(private userService: UserService, private administrationRequest: AdministrationRequestService) {
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
    this.directory.healthCard = '776422135432133';
    this.directory.lastName = 'Moreau';
    this.directory.sexe = 'F';
    this.directory.socialSecurity = 'Aucun';
    this.directory.zipcode = '77164';
    this.directory.fixe = '01.64.64.15.98';

    this.directory.profession = 'Medecin';
    this.directory.specialisation = 'Protesiste';

  }

  ngOnInit() {
    this.administrationRequest.getAdminFilePatient(this.userService.getPatientIdSelected()).then(data => {
      console.log(data);
    }).catch(err => {
      console.log(err);
    });
  }

  modifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    this.manageFile.state = ManageFile.State.Edited;
  }

  cancelModif() {
    console.log(this.oldDirectory);
    this.directory = this.oldDirectory;
    this.manageFile.state = ManageFile.State.Consulted;
  }
}
