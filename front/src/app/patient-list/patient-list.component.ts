import {Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import {Router} from '@angular/router';
import {PatientListServiceService} from './patient-list-service.service';


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


  constructor(private router: Router, private patientService: PatientListServiceService) {
  }

  ngOnInit() {
    this.patientService.getPatients().then(data => {
      console.log(data);
      this.listPatients = data;
    });
  }

  onClicOnPatient(id) {
    console.log('oui ' + id);
    this.router.navigate(['/administrationfile']);
  }

}
