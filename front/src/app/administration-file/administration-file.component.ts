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
  directory: any = {};

  isEmploye = true;
  oldDirectory: any;
  manageFile: ManageFile = new ManageFile();

  constructor(private userService: UserService, private administrationRequest: AdministrationRequestService) {

  }

  ngOnInit() {
    this.administrationRequest.getAdminFilePatient(this.userService.getPatientIdSelected()).then(data => {
      console.log(data);
      this.directory = data;
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
