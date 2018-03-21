import {Component, ElementRef, OnInit, Pipe, PipeTransform, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {PatientListServiceService} from './patient-list-service.service';
import {UserService} from '../user.service';


@Pipe({
  name: 'search'
})

export class SearchPipe implements PipeTransform {
  public transform(value, keys: string, term: string) {
    if (!term) {
      return value;
    }
    return (value || []).filter((item) => keys.split(',').some(key => item.hasOwnProperty(key) && new RegExp(term, 'gi').test(item[key])));
  }
}

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css'],
  providers: [PatientListServiceService]
})


export class PatientListComponent implements OnInit {
  listPatients: any;
  firstName = true;
  lastName = true;
  gender = true;
  country = true;
  birthDate = true;
  navVisible: boolean;
  user: any;

  search = {
    firstName: true,
    lastName: true,
}

  constructor(private router: Router, private patientService: PatientListServiceService, private userService: UserService) {
    this.user = this.userService;
  }

  ngOnInit() {
    if (this.user.isSecretary()){
      this.patientService.getAllPatients().then(data => {
        this.listPatients = data;
      });
    }else {
      this.patientService.getPatients(this.user.getId()).then(data => {
        this.listPatients = data;
      });
    }
    this.userService.setPatient(null);
  }

  onClicOnPatient(patient) {
    this.userService.setPatient(patient);
    if (this.userService.isDoctor()){
      this.router.navigate(['doclist', { type: 'Consultation' }]);
    }else if (this.userService.isNurse()) {
      this.router.navigate(['doclist', { type: 'Prescription' }]);
    }else{
      this.router.navigate(['/administrationfile']);
    }
  }

  getItemsSearch(){
    let s = ''
    for (let i in this.search){
      if (this.search[i]){
        s += i + ",";
      }
    }
    return s;
  }
}
