import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {Patient} from "../patient";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../user.service";
import {PatientService} from "../patient.service";

@Component({
  selector: 'app-consultation-file',
  templateUrl: './consultation-file.component.html',
  styleUrls: ['./consultation-file.component.css', './../app.component.css']
})
export class ConsultationFileComponent implements OnInit {
  directory = {
    motif: '',
    observation: ''
  }
  oldDirectory;
  manageFile: ManageFile = new ManageFile();
  userFirstName: string;
  userLastName: string;
  userStatus: string;

  patientFirstName: string;
  patientLastName: string;

  constructor(private route: ActivatedRoute, private userService: UserService, private patientService: PatientService) {
  }

  ngOnInit() {
    this.userFirstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.userLastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.userStatus = this.userService.getStatus() ? this.userService.getStatus() : '';

    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.directory.motif = 'Patient souvrant de maux de ventre';
        this.directory.observation = 'Rythme cardiaque normale\n' +
          '      Respiration normale\n' +
          '      Grosseur aux niveau de l\'abdomen';
      }

    });

    this.patientFirstName = this.patientService.getfirstName() ? this.patientService.getfirstName(): 'null';
    this.patientLastName = this.patientService.getlastName() ? this.patientService.getlastName(): 'oups';

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
