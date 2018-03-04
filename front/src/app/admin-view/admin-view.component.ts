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

  modalRef: BsModalRef;


  constructor(private request: WebApiPromiseService, private cdr: ChangeDetectorRef, private modalService: BsModalService) {
  }

  ngOnInit() {
    this.request.getStaffs().then(data => {
      this.listStaff = data;
      this.listStaff.forEach(staff => {
        staff.hospital = [];
        staff.pole = [];
        staff.service = [];
      });
    }).catch(err => {
      console.log(err);
    });

    this.request.getHospitals().then(data => {
      this.listHospital = data;
      this.cdr.detectChanges();
    }).catch(err => {
      console.log(err);
    });
  }

  ngAfterViewChecked(): void {
    this.cdr.detectChanges();
  }

  onChangeHospital(hospital, staff) {
    staff.hospital = hospital;
    staff.pole = [];
    staff.service = [];
    staff.unity = [];
  }

  onChangePole(pole, staff) {
    staff.pole = pole;
    staff.service = [];
    staff.unity = [];
  }

  onChangeService(service, staff) {
    staff.service = service;
    staff.unity = [];
  }


  selectedHospital(hospital, staff) {
    if (1 === hospital.id) {
      staff.hospital = hospital;
    }
    return 1 === hospital.id;
  }


  selectedPole(pole, staff) {
    if (staff.node.level === 'pole' && staff.node.id === pole.id) {
      staff.pole = pole;
      return true;
    }
    return false;
  }


  modificationStaff(template: TemplateRef<any>) {
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


}
