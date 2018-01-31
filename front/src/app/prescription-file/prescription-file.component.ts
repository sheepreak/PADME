import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-prescription-file',
  templateUrl: './prescription-file.component.html',
  styleUrls: ['./prescription-file.component.css']
})
export class PrescriptionFileComponent implements OnInit {
  prescription: ''
  hopital: string
  date: string

  publish = false;
  modifyData = false;
  oldPrescription;


  constructor() { }

  ngOnInit() {
  }

  modifData() {
    this.oldPrescription = Object.assign({}, this.prescription);
    this.modifyData = true;
  }

  cancelModif() {
    console.log(this.oldPrescription);
    this.prescription = this.oldPrescription;
    this.modifyData = false;
  }

  publishData(){
    var today = new Date();
    var jj = today.getDay().toString();
    var mm = (today.getMonth()+1).toString();
    var aaaa = today.getFullYear();

    if (jj.length != 2){
      jj = "0".concat(jj);
    }
    if (mm.length != 2){
      mm = "0".concat(mm);
    }

    this.date = jj+"/"+mm+"/"+aaaa;
    this.hopital = "Hopital de Paris";
    this.publish = true;
  }
}
