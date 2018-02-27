import {Component, OnInit, TemplateRef} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {UserService} from '../user.service';
import {TranslateService} from '@ngx-translate/core';
import {AppComponent} from '../app.component';
import {TranslateModule, TranslateLoader} from '@ngx-translate/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {WebApiPromiseService} from '../web-api-promise.service';


@Component({
  selector: 'app-static-nav-bar',
  templateUrl: './static-nav-bar.component.html',
  styleUrls: ['./static-nav-bar.component.css']
})
export class StaticNavBarComponent implements OnInit {

  id: string;
  firstName: string;
  lastName: string;
  status: string;
  email: string;
  phone: string;
  address: string;

  visibility: boolean;

  modalRef: BsModalRef;
  config = {
    animated: true,
    keyboard: true,
    backdrop: true,
    ignoreBackdropClick: true
  };

  constructor(private userService: UserService, private modalService: BsModalService, private request: WebApiPromiseService) {
    this.firstName = this.userService.getfirstName() ? this.userService.getfirstName() : '';
    this.lastName = this.userService.getlastName() ? this.userService.getlastName() : '';
    this.status = this.userService.getStatus() ? this.userService.getStatus() : '';
    this.address = this.userService.getAdress() ? this.userService.getAdress() : '';
    this.phone = this.userService.getPhone() ? this.userService.getPhone() : '';
    this.id = this.userService.getId();
    this.visibility = false;
  }

  ngOnInit(): void {

  }

  public loggout() {
    this.userService.loggout();
  }

  public changeVisibility() {
    this.visibility = !this.visibility;
  }


  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  updateStaff(form) {

    const body = {'id': this.userService.getId(), ...form.value};
    console.log(body);
    this.request.updateStaff(body).then(data => {
      console.log(data);
    }).catch(err => {
      console.log(err);
    });
  }
}




