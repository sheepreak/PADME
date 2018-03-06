import { Component, OnInit } from '@angular/core';
import {ManageFile} from "../manageFile";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-medical-doc-list',
  templateUrl: './medical-doc-list.component.html',
  styleUrls: ['./medical-doc-list.component.css']
})
export class MedicalDocListComponent implements OnInit {
  type: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      this.type = params['type'];
    });
  }

}
