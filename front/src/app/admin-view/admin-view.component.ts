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

  constructor(private request: WebApiPromiseService) {
  }

  ngOnInit() {
    this.request.getStaffs().then(data => {
      console.log(data);
      this.listStaff = data;
    }).catch(err => {
      console.log(err);
    });
  }

  onClicOnStaff(id) {

  }

}
