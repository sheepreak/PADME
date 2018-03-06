import {Component, OnInit} from '@angular/core';
import {ManageFile} from "../manageFile";
import {Patient} from "../patient";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-prescription-file',
  templateUrl: './prescription-file.component.html',
  styleUrls: ['./prescription-file.component.css', './../app.component.css']
})
export class PrescriptionFileComponent implements OnInit {
  prescription: string;
  oldPrescription;
  manageFile: ManageFile = new ManageFile();
  patient: Patient = new Patient("Jean", "Dujardin");
  firstName: string;
  lastName: string;
  status: string;


  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit() {
    this.firstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.lastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';


    let state;
    this.route.params.subscribe(params => {
      state = params['state'];
      if (state == 'new') {
        this.manageFile.state = ManageFile.State.New;
      } else {
        this.prescription = '2 boites de doliprane\n' +
          '      spasfond';
      }
    });
  }

  modifData() {
    this.oldPrescription = Object.assign({}, this.prescription);
    this.manageFile.state = ManageFile.State.Edited;
  }

  cancelModif() {
    console.log(this.oldPrescription);
    this.prescription = this.oldPrescription;
    this.manageFile.state = ManageFile.State.Consulted;
  }
}
