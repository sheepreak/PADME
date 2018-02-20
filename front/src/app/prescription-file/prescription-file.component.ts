import { Component, OnInit } from '@angular/core';
import {ManageFile} from "../manageFile";
import {Doctor} from "../doctor";
import {Patient} from "../patient";

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
  doctor: Doctor = new Doctor("Jean-Luc", "Portos", "Radiologue");


  constructor() {
    this.prescription = '2 boites de doliprane\n' +
      '      spasfond';
  }

  ngOnInit() {
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
