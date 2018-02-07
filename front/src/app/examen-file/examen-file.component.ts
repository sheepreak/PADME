import {Component, OnInit} from '@angular/core';
import {Image} from '../image';
import {ManageFile} from '../manageFile';
import {log} from "util";

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
/*
  orderForm: FormGroup;
  items: any[] = [];
  formBuilder: FormBuilder;
*/

  constructor() {
    this.directory.description = 'Radiographie simple de l\'epaule gauche avec clicher de face stricte et profile auxillaire';
    this.directory.motif = 'Suite à une chute d\'équitation, douleur à l\'epaule droit, et manque de mobilité';
    this.directory.observation = 'Fracture tassement de la face posterieur de la tếte humérale';
    this.imgMin = true;

    let i = new Image('Radio épaule profile auxillaire', '../../assets/epaule1.jpg');
    let ii = new Image('Radio épaule face stricte', '../../assets/epaule2.png');
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
    /*
    this.orderForm = this.formBuilder.group({
      customerName: '',
      email: '',
      items: this.formBuilder.array([ this.createItem() ])
    });
*/
  }

/*
  createItem(): FormGroup {
    return this.formBuilder.group({
      name: '',
      description: '',
      price: ''
    });
  }

  addItem(): void {
    this.items = this.orderForm.get('items') as FormArray;
    this.items.push(this.createItem());
  }
  */

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
}
