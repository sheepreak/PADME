import {AfterViewChecked, ChangeDetectionStrategy, Component, OnInit, Pipe, PipeTransform, TemplateRef} from '@angular/core';
import {WebApiPromiseService} from '../web-api-promise.service';
import {ChangeDetectorRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {AdminViewRequestService} from './admin-view-request.service';

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
  unitySeleted = [];


  constructor(private request: WebApiPromiseService, private cdr: ChangeDetectorRef,
              private modalService: BsModalService, private adminRequest: AdminViewRequestService) {
    this.request.getStaffs().then(data => {
      this.listStaff = data;

      this.request.getHospitals().then(data2 => {
        this.listHospital = data2;

        for (let i = 0; i < this.listStaff.length; i++) {
          this.hospitalSeleted[i] = this.listHospital[0];
          this.listPole[i] = this.hospitalSeleted[i].hierarchy;

          for (const p of this.hospitalSeleted[i].hierarchy) {
            if (p.id === this.listStaff[i].hierarchy[0]) {
              this.poleSeleted[i] = p;
              this.listService[i] = p.subNodes;
              break;
            }
          }

          if (this.listStaff[i].hierarchy[1] !== undefined && this.listService[i].length > 0) {
            for (const s of this.listService[i]) {
              if (s.id === this.listStaff[i].hierarchy[1]) {
                this.serviceSeleted[i] = s;
                this.listUnity[i] = s.subNodes;
                break;
              }
            }
          }

          if (this.listStaff[i].hierarchy[2] !== undefined && this.listUnity[i].length > 0) {
            for (const u of this.listUnity[i]) {
              if (u.id === this.listStaff[i].hierarchy[2]) {
                this.unitySeleted[i] = u;
                break;
              }
            }
          }


        }
      }).catch(err => {
        console.log(err);
      });


    }).catch(err => {
      console.log(err);
    });
  }

  ngOnInit() {
  }

  ngAfterViewChecked(): void {
    this.cdr.detectChanges();
  }


  onSubmit(template: TemplateRef<any>, index, staff) {
    let node;
    if (this.unitySeleted[index] != null) {
      node = this.unitySeleted[index];
    } else if (this.serviceSeleted[index] != null) {
      node = this.serviceSeleted[index];
    } else if (this.poleSeleted[index] != null) {
      node = this.poleSeleted[index];
    } else if (this.hospitalSeleted[index] != null) {
      node = this.hospitalSeleted[index];
    }

    console.log(node);
    console.log(index);
    this.adminRequest.updateNodeStaff(staff.id, node).then(data => {
      console.log(data);
      this.modalRef = this.modalService.show(template, {
        animated: true,
        keyboard: false,
        backdrop: false,
        ignoreBackdropClick: false
      });
      setTimeout(() => {
        this.modalRef.hide();
      }, 700);
    }).catch(err => {
      console.log(err);
    });


  }

  hospitalChange(hospital, index) {
    this.listPole[index] = hospital.hierarchy;
    this.poleSeleted[index] = [];
    this.serviceSeleted[index] = [];
    this.unitySeleted[index] = [];
  }

  poleChange(pole, index) {
    this.listService[index] = pole.subNodes;
    this.serviceSeleted[index] = [];
    this.unitySeleted[index] = [];
  }

  serviceChange(service, index) {
    this.listUnity[index] = service.subNodes;
    this.unitySeleted[index] = [];
  }


  openCreateNode(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, {
      animated: true,
      keyboard: false,
      backdrop: false,
      ignoreBackdropClick: false
    });

  }


  openCreateStaff(template: TemplateRef<any>){
    this.modalRef = this.modalService.show(template, {
      animated: true,
      keyboard: false,
      backdrop: false,
      ignoreBackdropClick: false
    });
  }

}
