import {Component, OnInit} from '@angular/core';

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

  constructor() {
  }

  ngOnInit() {
  }


}
