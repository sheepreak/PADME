import {Component, OnInit} from '@angular/core';


@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {


  listPatients = [];

  constructor() {

    this.listPatients.push({
      id: 1,
      firstname: 'charles',
      lastname: 'da silva costa',
      age: 23,
      birthday: '29/09/1994'
    });
    for (let i = 0; i < 10; i++) {
      this.listPatients.push({
        id: i + 2,
        firstname: 'aaaaa',
        lastname: 'sdfsdfsdfsdfsdf',
        age: 34,
        birthday: '12/09/1992'
      });
    }
  }

  ngOnInit() {
  }

  onClicOnPatient(id) {
    console.log('oui ' + id);
  }

}
