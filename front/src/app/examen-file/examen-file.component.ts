import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {State} from '../state';

@Component({
  selector: 'app-examen-file',
  templateUrl: './examen-file.component.html',
  styleUrls: ['./examen-file.component.css']
})
export class ExamenFileComponent implements OnInit {
  directory = {
    motif: '',
    description: '',
    result: '',
    observation: ''
  };
  img: Array<Image> = [];

  hopital: string;
  date: string;
  state: State = State.Consulted;
  tmp = true;

  oldDirectory;


  constructor() {
    this.state = State.Consulted;
    this.directory.description = 'Radiographie simple de l\'epaule gauche avec clicher de face stricte et profile auxillaire';
    this.directory.motif = 'Suite à une chute d\'équitation, douleur à l\'epaule droit, et manque de mobilité';
    this.directory.observation = 'Fracture tassement de la face posterieur de la tếte humérale';

    let i = new Image('Radio épaul profile auxillaire', '../../assets/epaule2.jpg');
    let ii = new Image('Radio épaul face stricte', '../../assets/epaule-face.jpg');
    console.log(i.name);
    this.img.push(i);
    this.img.push(ii);
  }

  ngOnInit() {
  }

  modifData() {
    this.oldDirectory = Object.assign({}, this.directory);
    // this.state = State.Edited;
  }

  cancelModif() {
    console.log(this.oldDirectory);
    this.directory = this.oldDirectory;
    // this.state = State.Consulted;
  }

  publishData() {
    var today = new Date();
    var jj = today.getDay().toString();
    var mm = (today.getMonth() + 1).toString();
    var aaaa = today.getFullYear();

    if (jj.length != 2) {
      jj = '0'.concat(jj);
    }
    if (mm.length != 2) {
      mm = '0'.concat(mm);
    }

    this.date = jj + '/' + mm + '/' + aaaa;
    this.hopital = 'Hopital de Paris';
    // this.state = State.Publish;
  }
}
