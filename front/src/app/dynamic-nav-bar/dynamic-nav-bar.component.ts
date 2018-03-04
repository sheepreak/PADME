import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-dynamic-nav-bar',
  templateUrl: './dynamic-nav-bar.component.html',
  styleUrls: ['./dynamic-nav-bar.component.css']
})
export class DynamicNavBarComponent implements OnInit {


  consultationIsCollapsed: boolean = true;
  examenIsCollapsed: boolean = true;
  prescriptionIsCollapsed: boolean = true;
  archiveIsCollapsed: boolean = true;
  addIsCollapsed: boolean = true;
  myDocIsCollapsed: boolean = true;

  user: UserService;

  navVisible = true;

  @ViewChild('sidebar')
  private documentSection: ElementRef;

  divWidth: number;

  constructor(private userService: UserService) {
    this.user = this.userService;
  }

  ngOnInit() {
  }


  closeNav() {
    this.divWidth = this.documentSection.nativeElement.offsetWidth * -1;
    this.navVisible = false;
  }

  openNav() {
    this.navVisible = true;
    this.divWidth = 0;
  }


}
