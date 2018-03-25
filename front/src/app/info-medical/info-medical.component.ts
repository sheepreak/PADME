import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";
import {ActivatedRoute} from "@angular/router";
import {ManageFile} from "../manageFile";

@Component({
  selector: 'app-info-medical',
  templateUrl: './info-medical.component.html',
  styleUrls: ['./info-medical.component.css']
})
export class InfoMedicalComponent implements OnInit {
  manageFile: ManageFile = new ManageFile();
  informations = {
    allergies: null,
    handicap: null
  };

  constructor(private userService: UserService, private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("ooooooooooooooooooooo");
    this.informations = this.userService.getInfoMedical().informations;
    console.log(this.informations);
    console.log(this.informations.allergies);
  }
}
