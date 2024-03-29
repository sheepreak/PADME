import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-dynamic-nav-bar',
  templateUrl: './dynamic-nav-bar.component.html',
  styleUrls: ['./dynamic-nav-bar.component.css'],
  host: {
    '(window:resize)': 'onResize($event)'
  }
})

export class DynamicNavBarComponent implements OnInit {
  consultationIsCollapsed: boolean = true;
  examenIsCollapsed: boolean = true;
  prescriptionIsCollapsed: boolean = true;
  archiveIsCollapsed: boolean = true;
  addIsCollapsed: boolean = true;
  myDocIsCollapsed: boolean = true;
  isPatientSelected: boolean = false;

  user: UserService;
  patientFirstName: string;
  patientLastName: string;

  navVisible: boolean;

  @ViewChild('sidebar')
  private documentSection: ElementRef;

  constructor(private userService: UserService) {
    this.user = this.userService;
  }

  ngOnInit() {
    this.closeNav();
  }

  closeNav() {
    this.userService.marginLeft = this.documentSection.nativeElement.offsetWidth * -1;
    this.navVisible = false;
    this.userService.marginBody = 0;
  }

  openNav() {
    this.navVisible = true;
    this.userService.marginLeft = 0;
    this.userService.marginBody = this.documentSection.nativeElement.offsetWidth;

    if (this.user.getPatient() != null) {
      this.patientFirstName = this.user.getPatient().firstName;
      this.patientLastName = this.user.getPatient().lastName;
    }
  }

  onResize(event) {
    if (!this.navVisible) {
      this.userService.marginBody = 0;
      this.userService.marginLeft = this.documentSection.nativeElement.offsetWidth * -1;
    }
  }
}
