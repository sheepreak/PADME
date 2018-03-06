import {AfterViewChecked, ChangeDetectionStrategy, Component, OnInit, Pipe, PipeTransform, TemplateRef} from '@angular/core';
import {WebApiPromiseService} from '../web-api-promise.service';
import {ChangeDetectorRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';

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
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-admin-view',
  templateUrl: './admin-view.component.html',
  styleUrls: ['./admin-view.component.css']
})


export class AdminViewComponent implements OnInit, AfterViewChecked {


  listStaff: any;
  listHospital: any;

  listPole = [];
  listService = [];
  listUnity = [];

  modalRef: BsModalRef;

  hospitalSeleted = [];
  poleSeleted = [];
  serviceSeleted = [];


  constructor(private request: WebApiPromiseService, private cdr: ChangeDetectorRef, private modalService: BsModalService) {
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

  ngAfterViewChecked(): void {
    this.cdr.detectChanges();
  }


  byIdHospital(item1, item2) {
    return item1.id === 1;
  }


  onSubmit(template: TemplateRef<any>, index) {
    console.log(this.hospitalSeleted[index]);
    console.log(this.poleSeleted[index]);
    console.log(this.serviceSeleted[index]);
    this.modalRef = this.modalService.show(template, {
      animated: true,
      keyboard: false,
      backdrop: false,
      ignoreBackdropClick: false
    });

    setTimeout(() => {
      this.modalRef.hide();
    }, 700);

  }

  hospitalChange(hospital, index) {
    this.listPole[index] = hospital.hierarchy;
  }

  poleChange(pole, index) {
    this.listService[index] = pole.subNodes;
  }

  serviceChange(service, index) {
    this.listUnity[index] = service.subNodes;
  }

}
