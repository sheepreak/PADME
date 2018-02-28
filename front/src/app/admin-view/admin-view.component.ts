import {Component, OnInit, Pipe, PipeTransform} from '@angular/core';
import {WebApiPromiseService} from '../web-api-promise.service';


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
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})
export class AdminViewComponent implements OnInit {

  listStaff: any;
  listHospital: any;
  listPole = [];
  listService = [];


  constructor(private request: WebApiPromiseService) {
  }

  ngOnInit() {
    this.request.getStaffs().then(data => {
      this.listStaff = data;
    }).catch(err => {
      console.log(err);
    });

    this.request.getHospitals().then(data => {
      this.listHospital = data;
    }).catch(err => {
      console.log(err);
    });
  }

  onChangeHospital(index, hospital) {
    this.listPole[index] = hospital.hierarchy;
  }

  onChangePole(index, pole) {
    console.log(pole);
    this.listService[index] = pole.subNodes;
  }

}
