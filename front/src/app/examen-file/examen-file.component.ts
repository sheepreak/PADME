import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {ManageFile} from '../manageFile';
import {log} from "util";
import {Patient} from "../patient";
import {Doctor} from "../doctor";

@Component({
  selector: 'app-examen-file',
  templateUrl: './examen-file.component.html',
  styleUrls: ['./examen-file.component.css', './../app.component.css']
})

export class ExamenFileComponent implements OnInit {
  directory = {
    motif: '',
    description: '',
    result: '',
    observation: ''
  };
  img: Array<Image> = [];
  imgMin: boolean;
  manageFile: ManageFile = new ManageFile();
  oldDirectory;
  patient: Patient = new Patient("Jean", "Dujardin");
  doctor: Doctor = new Doctor("Jean-Luc", "Portos", "Radiologue");
  name: string;
  path: string;

  constructor() {
    this.directory.description = 'Radiographie simple de l\'epaule gauche avec clicher de face stricte et profile auxillaire';
    this.directory.motif = 'Suite à une chute d\'équitation, douleur à l\'epaule droit, et manque de mobilité';
    this.directory.observation = 'Fracture tassement de la face posterieur de la tếte humérale';
    this.imgMin = true;

    let i = new Image('Radio épaule profile auxillaire', '../../assets/img/photo/epaule1.jpg');
    let ii = new Image('Radio épaule face stricte', '../../assets/img/photo/epaule2.png');
    console.log(i.name);
    this.img.push(i);
    this.img.push(ii);

    if (this.manageFile.stateConsulted()) {
      log("Consulted");
    }else{
      log("Not consulted");
    }
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

  public changeImg(img: Image){
    img.changeImg();
  }

  public loadAllImg(){
    for (let i of this.img){
      i.loadImg();
    }
  }

  public addImg(){
    if (this.name != null && this.path != null) {

      let i = new Image(this.name, this.path);
      this.img.push(i);

      this.path = null;
      this.name = null;
    }
  }
}
