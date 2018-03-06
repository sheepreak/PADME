import {Component, OnInit} from '@angular/core';
import {ManageFile} from '../manageFile';
import {UserService} from '../user.service';
import {AdministrationRequestService} from './administration-request.service';
import {ActivatedRoute} from "@angular/router";
import {Patient} from "../patient";
import {PatientService} from "../patient.service";


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

  constructor(private route: ActivatedRoute, private userService: UserService, private administrationRequest: AdministrationRequestService, private patientService: PatientService) {
  }

  ngOnInit() {
    this.administrationRequest.getAdminFilePatient(this.userService.getPatientIdSelected()).then(data => {
      this.directory = data;
    }).catch(err => {
      console.log(err);
    });

    this.patientService.setPatient(this.directory.firstname, this.directory.lastname);

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      }
    });
  }

  modifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    this.manageFile.state = ManageFile.State.Edited;
  }

  cancelModif() {
    this.directory = this.oldDirectory;
    this.manageFile.state = ManageFile.State.Consulted;
  }
}
