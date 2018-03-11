import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
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
  isThereConsultation = true;
  isThereExamen = true;
  isTherePrescription = true;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient, private userService: UserService, private medicalDocService: MedicalDocService) {
  }

  ngOnInit() {
    let id = this.userService.getIdMedicalFolder();
    console.log("Id Medical Folder "+ id);

    if (id == null) {
      this.isThereConsultation = false;
      this.isThereExamen = false;
      this.isTherePrescription = false;
      this.listDoc = [];
    } else {
      this.medicalDocService.getDocuments(this.userService.getIdMedicalFolder()).then(data => {
        this.listDoc = data;
        this.isThereConsultation = true;
        this.isThereExamen = true;
        this.isTherePrescription = true;
      });
    }

    this.route.params.subscribe(params => {
      this.type = params['type'];
    });
  }

  onClicOnConsultation(consultation) {
    this.userService.setConsultation(consultation);
    this.router.navigate(['/consultationfile']);
  }

  onClicOnExamen(examen) {
    this.userService.setExamen(examen);
    this.router.navigate(['/examenfile']);
  }

  onClicOnPrescription(prescription) {
    this.userService.setPrescription(prescription);
    this.router.navigate(['/prescriptionfile']);
  }
}
