import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "../user.service";
import {MedicalDocService} from "./medical-doc.service";

@Component({
  selector: 'app-medical-doc-list',
  templateUrl: './medical-doc-list.component.html',
  styleUrls: ['./medical-doc-list.component.css']
})
export class MedicalDocListComponent implements OnInit {
  type: string;
  listDoc: any;

  constructor(private route: ActivatedRoute, private http: HttpClient, private userService: UserService, private medicalDocService: MedicalDocService) {
  }

  ngOnInit() {
    this.medicalDocService.getDocuments().then(data => {
      this.listDoc = data;
    });
    //this.userService.setPatient(null);

    this.route.params.subscribe(params => {
      this.type = params['type'];
    });
  }



}
