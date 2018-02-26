import {Component, OnInit} from '@angular/core';
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

  constructor(private userService: UserService) {
    this.user = this.userService ;
  }

  ngOnInit() {
  }


}
