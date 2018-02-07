import { Component, OnInit } from '@angular/core';
import {ManageFile} from "../manageFile";

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

  constructor() {
    this.directory.motif = 'Patient souvrant de maux de ventre';
    this.directory.observation = 'Rythme cardiaque normale\n' +
      '      Respiration normale\n' +
      '      Grosseur aux niveau de l\'abdomen';
  }

  ngOnInit() {
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
